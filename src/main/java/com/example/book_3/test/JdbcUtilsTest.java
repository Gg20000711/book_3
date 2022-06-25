package com.example.book_3.test;

import com.example.book_3.utils.JdbcUtils;

import java.sql.Connection;

/**
 * @author 高先锋
 * @version 1.0
 */
public class JdbcUtilsTest {


    /**
     *  
     *  @author 高先锋
     *  @date 2022-06-04 13:46:31 
     *  @return
     **/
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }
    }
}
