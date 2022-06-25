package com.example.book_3.service;

import com.example.book_3.pojo.Book;
import com.example.book_3.pojo.Page;

import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-12 12:35
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(Integer id);
    public void updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();
    public Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
