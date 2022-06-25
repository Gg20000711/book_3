package com.example.book_3.web;

import com.example.book_3.pojo.User;
import com.example.book_3.service.UserService;
import com.example.book_3.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 高先锋好帅
 * @date 2022-06-04 22:08
 */
public class LoginServlet extends HttpServlet {
    //创建一个UserServiceImpl对象
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用UserService处理业务
        User loginUser = userService.login(new User(username,password,null));
        if (loginUser == null){
            System.out.println("登录失败");
            //跳回登录页面
            //把错误信息和回显的表单信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            System.out.println("登录成功");
            //保存用户登录后的信息到session域中
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
