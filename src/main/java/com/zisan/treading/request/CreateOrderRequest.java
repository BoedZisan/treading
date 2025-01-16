package com.zisan.treading.request;

import com.zisan.treading.domain.OrderType;

public class CreateOrderRequest {

    private String coinId;
    private double quantity;
    private OrderType orderType;

    // Getter and Setter for coinId
    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    // Getter and Setter for quantity
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for orderType
    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
