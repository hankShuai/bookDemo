package com.demo.dao;

import com.demo.pojo.User;

public interface UserDao {

    public User queryUserByUsername(String username);

    public  int SaveUser(User user);

    public  User  queryUserAll(String username, String password);
}
