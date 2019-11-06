package com.tsystems.tshop.security;

import java.util.Date;

public class JwtResponse {

    private final String token;
    private final Date expiresAt;

    public JwtResponse(String token, Date expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }
}
