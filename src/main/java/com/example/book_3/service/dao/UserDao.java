package com.example.book_3.service.dao;

import com.example.book_3.service.pojo.User;

/**
 * @author 高先锋好帅
 * @date 2022-06-04 14:25
 */
public interface UserDao {


    /**
     * 通过用户名查询用户信息
     * @author 高先锋
     * @date 2022-06-04 14:26:55
     * @param username
     * @return：如果返回null，说明没有这个用户，反之亦然
     **/
    public User queryUserByUsername(String username);

    /**
     * 通过用户名和密码查询用户信息
     * @author 高先锋
     * @date 2022-06-04 14:29:01
     * @param username
     * @param password
     * @return：如果返回null，说明用户名或者密码错误，反之亦然
     **/
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * 保存用户，返回-1表示保存失败，反之亦然
     * @author 高先锋
     * @date 2022-06-04 14:30:46
     * @param user
     * @return：返回-1表示保存失败，反之亦然
     **/
    public int saveUser(User user);
}
