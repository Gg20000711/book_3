package com.example.book_3.service;

import com.example.book_3.pojo.Cart;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 16:44
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
