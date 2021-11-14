package ru.ibs.security.jwt;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TokensPair {

    String accessToken;
    String refreshToken;


}
