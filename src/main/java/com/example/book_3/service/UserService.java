package com.example.book_3.service;

import com.example.book_3.pojo.User;

/**
 * @author 高先锋好帅
 * @date 2022-06-04 15:45
 */
public interface UserService {

    /**
     * 注册用户
     * @author 高先锋
     * @date 2022-06-04 15:46:19
     * @param user
     * @return
     **/
    public void registUser(User user);

    /**
     * 登录
     * @author 高先锋
     * @date 2022-06-04 15:47:26
     * @param user
     * @return
     **/
    public User login(User user);

    /**
     * 检查用户名是否可用，返回true表示用户名已经存在，返回FALSE表示用户名可以用
     * @author 高先锋
     * @date 2022-06-04 15:48:33
     * @param username
     * @return
     **/
    public boolean existsUsername(String username);
}
