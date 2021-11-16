package ru.ibs.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ibs.security.jwt.JwtProvider;
import ru.ibs.security.jwt.TokensPair;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.ibs.security.jwt.JwtProvider.BEARER_PREFIX;

@RestController
@AllArgsConstructor
@RequestMapping("/update")
public class TokenController {


    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/token")
    public TokensPair refreshTokens(Authentication authentication, HttpServletResponse response) throws IOException {
        TokensPair tokensPair = new TokensPair();
        tokensPair.setAccessToken(jwtProvider.createToken(authentication).replace(BEARER_PREFIX, ""));
        tokensPair.setRefreshToken(jwtProvider.createRefreshToken(authentication).replace(BEARER_PREFIX, ""));
        response.addHeader(HttpHeaders.AUTHORIZATION, tokensPair.getAccessToken());
        response.setContentType("text/json");
        return tokensPair;

    }
}
