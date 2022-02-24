package com.swagger.demo.service.impl;

import com.swagger.demo.bean.UserBean;
import com.swagger.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserBean getUserById(int id) {
        return null;
    }

    @Override
    public List<UserBean> getUserAll() {
        return null;
    }

    @Override
    public UserBean loginIn(String name, String password) {
        return null;
    }
}
