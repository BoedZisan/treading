package com.zisan.treading.repository;

import com.zisan.treading.modal.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepository extends JpaRepository <ForgotPasswordToken,String>{

    ForgotPasswordToken findByUserId(Long userId);
}