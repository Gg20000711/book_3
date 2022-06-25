package com.example.book_3.web;

import com.example.book_3.pojo.User;
import com.example.book_3.service.UserService;
import com.example.book_3.service.impl.UserServiceImpl;
import com.example.book_3.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author 高先锋好帅
 * @date 2022-06-06 22:58
 */
public class UserServle extends BaseServlet {
    //创建一个UserServiceImpl对象
    UserService userService = new UserServiceImpl();


//        String action = req.getParameter("action");
//        if ("login".equals(action)) {
//            login(req, resp);
//
//        } else if ("regist".equals(action)) {
//            regist(req, resp);
//        }


    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }


        public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理login的请求");
        User user =  WebUtils.copyParamToBean(req.getParameterMap(),new User());
//        try {
//            User user = new User();
//            System.out.println("注入之前：" + user);
//            BeanUtils.populate(user,req.getParameterMap());
//            System.out.println("注入之后：" + user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (token != null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                System.out.println("用户名[" + username + "]已存在!");
//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(new User(null, username, password, email));
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
