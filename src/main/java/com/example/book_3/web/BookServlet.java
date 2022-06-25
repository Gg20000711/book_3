package com.example.book_3.web;

import com.example.book_3.pojo.Book;
import com.example.book_3.pojo.Page;
import com.example.book_3.service.impl.BookServiceImpl;
import com.example.book_3.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-12 13:08
 */
public class BookServlet extends BaseServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo += 1;
        //1.将请求的参数封装成book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.addBook(book);
        //要用请求重定向
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"),0);
        bookService.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的参数封装成Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2.执行修改操作
        bookService.updateBook(book);
        //3.重定向/pages/manager/book_manager.jsp页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        //将请求保存到request域中
        req.setAttribute("books",books);
        //请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.获取请求的图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2.根据id获取图书
        Book book = bookService.queryBookById(id);
        //3.将图书放在request域中
        req.setAttribute("book",book);
        //4.将页面跳转到pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }


    protected void page(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //1.获取请求的参数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService中的page方法
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.将结果保存到request域中
        req.setAttribute("page",page);
        //4.请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
