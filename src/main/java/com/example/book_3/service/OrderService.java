package com.example.book_3.service;

import com.example.book_3.pojo.Cart;
import com.example.book_3.pojo.Order;
import com.example.book_3.pojo.Page;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 16:44
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> queryForAllOrder();
    public Page<Order> queryForPageOrders(int pageNo, int pageSize);
    public Page<Order> queryForPageOrdersByUserId(int userId,int pageNo,int pageSize);
}
