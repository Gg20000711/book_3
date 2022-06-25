package com.example.book_3.service.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 高先锋好帅
 * @date 2022-06-25 10:40
 */
public class OrderServiceImplTest {
    OrderServiceImpl orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
    }

    @Test
    public void queryForAllOrder() {

        System.out.println(orderService.queryForAllOrder());
    }

    @Test
    public void queryForPageOrders() {

        System.out.println(orderService.queryForPageOrders(1,4));
    }


    @Test
    public void queryForPageOrdersByUserId() {

        System.out.println(orderService.queryForPageOrdersByUserId(4,1,4));
    }
}