package com.zisan.treading.service;

import com.zisan.treading.modal.PaymentDetails;
import com.zisan.treading.modal.User;

public interface PaymentDetailsService {


        public PaymentDetails addPaymentDetails(
                String accountNumber,
                String accountHolderName,
                String ifsc,
                String bankName,
                User user
        );

        public PaymentDetails getUsersPaymentDetails(User user);


}
