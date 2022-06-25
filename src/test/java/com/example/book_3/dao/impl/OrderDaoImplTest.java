package com.example.book_3.dao.impl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 高先锋好帅
 * @date 2022-06-25 10:37
 */
public class OrderDaoImplTest {
    OrderDaoImpl orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
    }

    @Test
    public void queryForAllOrder() {
        System.out.println(orderDao.queryForAllOrder());
    }

    @Test
    public void queryForPageOrder() {

        System.out.println(orderDao.queryForPageOrder(1,4));
    }

    @Test
    public void queryForOrderCount() {

        System.out.println(orderDao.queryForOrderCount());
    }

    @Test
    public void queryForPageOrderByUserId() {

        System.out.println(orderDao.queryForPageOrderByUserId(4,0,4));
    }

    @Test
    public void queryForOrderCountByUserId() {

        System.out.println(orderDao.queryForOrderCountByUserId(4));
    }
}