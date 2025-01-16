package com.zisan.treading.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import com.zisan.treading.domain.PaymentMethod;
import com.zisan.treading.modal.PaymentOrder;
import com.zisan.treading.modal.User;
import com.zisan.treading.response.PaymentResponse;

public interface PaymentService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    Boolean ProccedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLing(User user, Long amount, Long orderID) throws RazorpayException;

    PaymentResponse createStripePaymentLing(User user, Long amount,Long orderId) throws StripeException;
}
