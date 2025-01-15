package com.zisan.treading.service;

import com.zisan.treading.domain.OrderType;
import com.zisan.treading.modal.Coin;
import com.zisan.treading.modal.Order;
import com.zisan.treading.modal.OrderItem;
import com.zisan.treading.modal.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, OrderItem orderItem, OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOrdersOfUser(Long userId, OrderType orderType, String assetSymbol);

    Order processOrder(Coin coin, double quantity, OrderType orderType, User user) throws Exception;
}
