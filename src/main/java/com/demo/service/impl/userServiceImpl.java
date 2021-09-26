package com.demo.service.impl;

import com.demo.dao.UserDao;
import com.demo.pojo.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private UserDao userDao ;
    @Override
    public void registUser(User user) {
        userDao.SaveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserAll(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUser(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }else {
            return true;
        }
    }
}
