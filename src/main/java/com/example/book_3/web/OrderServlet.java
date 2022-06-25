package com.example.book_3.web;

import com.example.book_3.pojo.Cart;
import com.example.book_3.pojo.User;
import com.example.book_3.service.impl.OrderServiceImpl;
import com.example.book_3.pojo.Cart;
import com.example.book_3.pojo.User;
import com.example.book_3.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 高先锋好帅
 * @date 2022-06-24 18:07
 */
public class OrderServlet extends BaseServlet{
    OrderServiceImpl orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //先从session域中获取cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //从session域中获取userId
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        Integer userId = loginUser.getId();
        //调用OrderService中的creatOrder方法创建Order对象
        String orderId = orderService.createOrder(cart,userId);

        /**  这样可能会有重复创建问题，采用重定向方案
        //把Order对象保存在request域中
        request.setAttribute("orderId",orderId);
        //请求转发
        request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request,response);

         */

        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
