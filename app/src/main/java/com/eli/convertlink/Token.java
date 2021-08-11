package com.eli.convertlink;

import com.google.gson.annotations.SerializedName;

class Token {
    @SerializedName(value = "access_token")
    private String accessToken;
    private String token_type;
    private int expires_in;
    private long expiration = 0;

    public Token(String accessToken, String tokenType, int expiresIn) {
        this.accessToken = accessToken;
        this.token_type = tokenType;
        this.expires_in = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public int getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(int expiresIn) {
        this.expires_in = expiresIn;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration + expires_in*1000;
    }

    public boolean expired() {
        return System.currentTimeMillis() >= expiration;
    }

    @Override
    public String toString() {
        return "Token{" +
                "accessToken='" + accessToken + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", expiration=" + expiration +
                '}';
    }
}
