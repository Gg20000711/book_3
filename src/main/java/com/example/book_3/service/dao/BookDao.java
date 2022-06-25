package com.example.book_3.service.dao;

import com.example.book_3.service.pojo.Book;

import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-10 18:00
 */
public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();
    public Integer queryForPageTotalCount();
    public List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
