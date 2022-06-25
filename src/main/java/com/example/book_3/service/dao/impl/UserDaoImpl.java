package com.example.book_3.service.dao.impl;

import com.example.book_3.service.dao.UserDao;
import com.example.book_3.service.pojo.User;

/**
 * @author 高先锋好帅
 * @date 2022-06-04 14:36
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    /**
     * 保存用户，返回-1表示保存失败，其他表示SQL语句影响的行数
     * @author 高先锋
     * @date 2022-06-04 15:10:23
     * @param user：保存的用户
     * @return：返回-1表示保存失败，其他表示SQL语句影响的行数
     **/
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());

    }
}
