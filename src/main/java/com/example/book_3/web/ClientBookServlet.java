package com.example.book_3.web;

import com.example.book_3.pojo.Book;
import com.example.book_3.pojo.Page;
import com.example.book_3.service.impl.BookServiceImpl;
import com.example.book_3.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 高先锋好帅
 * @date 2022-06-20 19:30
 */
public class ClientBookServlet extends BaseServlet{

    private BookServiceImpl bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService中的page方法
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");
        //3.将结果保存到request域中
        req.setAttribute("page",page);
        //4.请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2.调用BookService中的page方法
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }
       page.setUrl(stringBuilder.toString());
        //3.将结果保存到request域中
        req.setAttribute("page",page);
        //4.请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
