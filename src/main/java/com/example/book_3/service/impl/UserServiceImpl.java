package com.example.book_3.service.impl;

import com.example.book_3.dao.UserDao;
import com.example.book_3.dao.impl.UserDaoImpl;
import com.example.book_3.pojo.User;
import com.example.book_3.service.UserService;

/**
 * @author 高先锋好帅
 * @date 2022-06-04 15:52
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();
    @Override
    public void registUser(User user) {

        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username) == null) {
            return false;
        }else {
            return true;
        }
    }
}
