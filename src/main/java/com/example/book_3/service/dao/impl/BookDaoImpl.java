package com.example.book_3.service.dao.impl;

import com.example.book_3.service.dao.BookDao;
import com.example.book_3.service.pojo.Book;

import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-10 18:12
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , " +
                "`stock` , `img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),
                book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(int id) {
         String sql = "delete from t_book where id = ?";
         return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?," +
                "`img_path`=? where `id`=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales()
        ,book.getStock(),book.getImgPath(),book.getId());

    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , " +
                "`stock` , `img_path` imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);

    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , " +
                "`stock` , `img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select COUNT(*) from t_book";
        Number pageTotalCount = (Number) queryForSingleValue(sql);
        return pageTotalCount.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , " +
                "`stock` , `img_path` imgPath from t_book limit ?,?";
            return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select COUNT(*) from t_book where price between ? and ?";
        Number pageTotalCount = (Number) queryForSingleValue(sql,min,max);
        return pageTotalCount.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , " +
                "`stock` , `img_path` imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
