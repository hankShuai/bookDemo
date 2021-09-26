package com.demo.service;

import com.demo.pojo.Cart;

public interface OrderService {
    String createOrder(Cart cart,Integer userId);
}
