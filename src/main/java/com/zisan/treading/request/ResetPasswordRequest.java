package com.zisan.treading.request;

public class ResetPasswordRequest {

    private String otp;
    private String password;

    // Getter for OTP
    public String getOtp() {
        return otp;
    }

    // Setter for OTP
    public void setOtp(String otp) {
        this.otp = otp;
    }

    // Getter for Password
    public String getPassword() {
        return password;
    }

    // Setter for Password
    public void setPassword(String password) {
        this.password = password;
    }
}
