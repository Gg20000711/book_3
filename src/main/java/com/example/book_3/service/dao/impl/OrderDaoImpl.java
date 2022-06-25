package com.example.book_3.service.dao.impl;

import com.example.book_3.service.dao.OrderDao;
import com.example.book_3.service.pojo.Order;

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
}
