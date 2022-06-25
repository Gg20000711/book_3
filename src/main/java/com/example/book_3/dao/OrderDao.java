package com.example.book_3.dao;

import com.example.book_3.pojo.Order;

import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 15:51
 */
public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryForAllOrder();
    public List<Order> queryForPageOrder(int begin,int pageSize);
    public Integer queryForOrderCount();
    public List<Order> queryForPageOrderByUserId(int userId,int begin,int pageSize);
    public Integer queryForOrderCountByUserId(int userId);
}
