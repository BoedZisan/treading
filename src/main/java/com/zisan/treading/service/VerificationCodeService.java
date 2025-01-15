package com.zisan.treading.service;

import com.zisan.treading.domain.VerificationType;
import com.zisan.treading.modal.User;
import com.zisan.treading.modal.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);

    void deleteVerificationCodeById(VerificationCode verificationCode);
}
