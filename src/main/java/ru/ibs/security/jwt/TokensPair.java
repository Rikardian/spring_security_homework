package ru.ibs.security.jwt;

import lombok.Data;

@Data
public class TokensPair {

    String accessToken;
    String refreshToken;


}
