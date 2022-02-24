package com.swagger.demo.service;

import com.swagger.demo.bean.UserBean;

import java.util.List;

public interface UserService {
    UserBean getUserById(int id);

    List<UserBean> getUserAll();

    UserBean loginIn(String name, String password);
}
