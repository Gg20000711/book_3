package com.example.book_3.dao.impl;

import com.example.book_3.dao.OrderItemDao;
import com.example.book_3.pojo.OrderItem;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 16:02
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
