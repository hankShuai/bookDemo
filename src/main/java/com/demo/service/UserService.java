package com.demo.service;

import com.demo.pojo.User;

public interface UserService {

    public void registUser(User user);

    public User login(User user);

    public boolean existsUser(String username);
}
