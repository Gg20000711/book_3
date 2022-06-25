package com.example.book_3.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author 高先锋
 * @version 1.0
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;

    static {

        try {
            Properties properties = new Properties();
            //读取属性文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            //输出获取的连接
//            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * @return
     * @author 高先锋
     * @date 2022-06-04 13:19:43
     **/
    public static Connection getConnection() {
        Connection connection = null;
        try {

            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

    /**
     *
     * @author 高先锋
     * @date 2022-06-04 13:23:18
     * @param connection
     * @return
     **/
    public static void close(Connection connection) {
        if (connection != null) {
            try {

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(getConnection());
    }
}
