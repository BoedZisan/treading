package com.zisan.treading.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zisan.treading.domain.VerificationType;
import jakarta.persistence.Embeddable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VerificationType sendTo;

    // Getter for isEnabled
    public boolean isEnabled() {
        return isEnabled;
    }

    // Setter for isEnabled
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    // Getter for sendTo
    public VerificationType getSendTo() {
        return sendTo;
    }

    // Setter for sendTo
    public void setSendTo(VerificationType sendTo) {
        this.sendTo = sendTo;
    }
}
