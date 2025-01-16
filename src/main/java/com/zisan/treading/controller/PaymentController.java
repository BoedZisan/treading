package com.zisan.treading.controller;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.zisan.treading.domain.PaymentMethod;
import com.zisan.treading.modal.PaymentOrder;
import com.zisan.treading.modal.User;
import com.zisan.treading.response.PaymentResponse;
import com.zisan.treading.service.PaymentService;
import com.zisan.treading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt
    ) throws Exception, RazorpayException, StripeException {

        User user = userService.findUserProfileByJwt(jwt);
        PaymentResponse paymentResponse;

        PaymentOrder order = paymentService.createOrder(user, amount, paymentMethod);

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            paymentResponse = paymentService.createRazorpayPaymentLing(user, amount, order.getId());
        } else {
            paymentResponse = paymentService.createStripePaymentLing(user, amount, order.getId());
        }

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }

}
