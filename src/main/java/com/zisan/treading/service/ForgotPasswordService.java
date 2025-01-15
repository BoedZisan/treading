package com.zisan.treading.service;


import com.zisan.treading.domain.VerificationType;
import com.zisan.treading.modal.ForgotPasswordToken;
import com.zisan.treading.modal.User;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user,
                                    String id,
                                    String otp,
                                    VerificationType verificationType,
                                    String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);
}


