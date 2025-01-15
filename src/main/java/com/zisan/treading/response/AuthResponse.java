package com.zisan.treading.response;

public class AuthResponse {

    private String jwt;
    private boolean status;
    private String message;
    private boolean isTwoFactorAuthEnabled;
    private String session;

    // Getter and Setter for jwt
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    // Getter and Setter for status
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Getter and Setter for message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for isTwoFactorAuthEnabled
    public boolean isTwoFactorAuthEnabled() {
        return isTwoFactorAuthEnabled;
    }

    public void setTwoFactorAuthEnabled(boolean isTwoFactorAuthEnabled) {
        this.isTwoFactorAuthEnabled = isTwoFactorAuthEnabled;
    }

    // Getter and Setter for session
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
