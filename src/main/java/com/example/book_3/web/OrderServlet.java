package com.example.book_3.web;

import com.example.book_3.pojo.*;
import com.example.book_3.service.impl.OrderServiceImpl;
import com.example.book_3.pojo.Cart;
import com.example.book_3.pojo.User;
import com.example.book_3.service.impl.OrderServiceImpl;
import com.example.book_3.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.WeakHashMap;

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

    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.queryForAllOrder();
        request.getSession().setAttribute("orders",orders);
        response.sendRedirect(request.getContextPath() + "/pages/manager/order_manager.jsp");
    }

    protected void showPageOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
//        List<Order> orders = orderService.queryForPageOrders(pageNo,pageSize);
//        request.setAttribute("pageOrders",orders);
        Page<Order> page = orderService.queryForPageOrders(pageNo,pageSize);
        page.setUrl("orderServlet?action=showPageOrders");
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);

    }
    protected void showPageOrdersByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        Integer pageNo = WebUtils.parseInt(request.getParameter("pageNo"),1);
        Integer pageSize = WebUtils.parseInt(request.getParameter("pageSize"),4);
        Page<Order> page = orderService.queryForPageOrdersByUserId(userId,pageNo,pageSize);
        page.setUrl("orderServlet?action=showPageOrdersByUserId");
        request.setAttribute("page",page);

        request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);

    }
    }
