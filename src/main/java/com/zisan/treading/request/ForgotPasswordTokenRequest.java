package com.zisan.treading.request;

import com.zisan.treading.domain.VerificationType;

public class ForgotPasswordTokenRequest {

    private String sendTo;
    private VerificationType verificationType;

    // Getter and Setter for sendTo
    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    // Getter and Setter for verificationType
    public VerificationType getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VerificationType verificationType) {
        this.verificationType = verificationType;
    }
}
