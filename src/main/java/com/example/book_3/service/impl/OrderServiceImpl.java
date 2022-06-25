package com.example.book_3.service.impl;

import com.example.book_3.dao.impl.BookDaoImpl;
import com.example.book_3.dao.impl.OrderDaoImpl;
import com.example.book_3.dao.impl.OrderItemDaoImpl;
import com.example.book_3.pojo.*;
import com.example.book_3.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 16:46
 */
public class OrderServiceImpl implements OrderService {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号，要唯一
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //将购物车中的每一个订单转化为一个个订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转化为每一个个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库中
            orderItemDao.saveOrderItem(orderItem);
            //更改库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + 1);
            book.setStock(book.getStock() - 1);
            bookDao.updateBook(book);


        }
        //清空购物车
        cart.clean();
        //返回值是订单号
        return orderId;

    }
}
