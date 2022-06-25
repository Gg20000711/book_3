package com.example.book_3.web;

import com.example.book_3.pojo.Book;
import com.example.book_3.pojo.Cart;
import com.example.book_3.pojo.CartItem;
import com.example.book_3.service.impl.BookServiceImpl;
import com.example.book_3.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 高先锋好帅
 * @date 2022-06-21 21:42
 */
public class CartServlet extends BaseServlet{
    private BookServiceImpl bookService = new BookServiceImpl();

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = WebUtils.parseInt(request.getParameter("id"),0);
        int count = WebUtils.parseInt(request.getParameter("count"),1);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

        protected void clean(HttpServletRequest request, HttpServletResponse response) throws IOException {

       //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            cart.clean();
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取要删除的对象的id
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
            //删除后重定向回去
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

        protected void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        response.sendRedirect(request.getHeader("Referer"));
        request.getSession().setAttribute("lastName",cartItem.getName());
    }
}
