package com.example.book_3.dao.impl;

import com.example.book_3.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 高先锋好帅
 * @date 2022-06-04 13:44
 */
public abstract class BaseDao {
    //使用Dbutils操作数据库
    private QueryRunner queryRunner = new QueryRunner();
/**
 * 用来增删改操作
 * @author
 * @date 2022-06-04 13:57:34
 * @param sql：SQL语句
 * @param args：SQL语句的参数
 * @return：int类型
 **/
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(connection);
        }
        return -1;
    }

    /**
     * 返回一条语句的查询
     * @author 高先锋
     * @date 2022-06-04 14:07:40
     * @param sql：SQL语句
     * @param type：返回值类型
     * @param args：SQL语句的参数
     * @return：泛型
     **/
    public <T> T queryForOne(Class<T> type, String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }



    /**
     * 返回多条语句的查询
     * @author 高先锋
     * @date 2022-06-04 14:07:40
     * @param sql：SQL语句
     * @param type：返回值类型
     * @param args：SQL语句的参数
     * @return：泛型
     **/
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }


    /**
     * 执行返回一行一列的SQL语句
     * @author 高先锋
     * @date 2022-06-04 15:08:09 
     * @param sql
     * @param args
     * @return
     **/
    public Object queryForSingleValue(String sql, Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection);
        }
        return null;
    }

}
