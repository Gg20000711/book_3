package com.example.book_3.dao.impl;

import com.example.book_3.dao.OrderDao;
import com.example.book_3.pojo.Order;

import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 15:58
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryForAllOrder() {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public List<Order> queryForPageOrder(int begin, int pageSize) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order limit ?,?";
        return queryForList(Order.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForOrderCount() {
        String sql = "select COUNT(*) from t_order";
        Number orderAllCount = (Number) queryForSingleValue(sql);
        return orderAllCount.intValue();
    }

    @Override
    public List<Order> queryForPageOrderByUserId(int userId, int begin, int pageSize) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where `user_id` = ? limit ?,?";
        return queryForList(Order.class,sql,userId,begin,pageSize);
    }

    @Override
    public Integer queryForOrderCountByUserId(int userId) {
        String sql = "select count(*) from t_order where `user_id` = ?";
        Number orderCountByUserId =(Number) queryForSingleValue(sql,userId);
        return orderCountByUserId.intValue();
    }



}
