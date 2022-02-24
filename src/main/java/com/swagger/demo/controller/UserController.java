package com.swagger.demo.controller;

import com.swagger.demo.bean.UserBean;
import com.swagger.demo.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "用户操作模块API")//作用在模块API类上，对API模块进行说明
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    UserBean user1 = new UserBean(1,"zhangsan",18,"123");
    UserBean user2 = new UserBean(2,"lisi",19,"234");
    UserBean user3 = new UserBean(3,"wangwu",20,"345");
    List<UserBean> userBeans = List.of(user1,user2,user3);
    List users = new ArrayList(userBeans);

    @ApiOperation("根据ID查询用户")//作用在API方法上，对操作进行说明
    @GetMapping("/{id}")
    public UserBean getUserById(@PathVariable("id") int id){
        UserBean userBean = null;
        for (UserBean user : userBeans){
            if (user.getId()==id){
                userBean = user;
            }
        }
        return userBean;
    }

    @ApiOperation("根据账号密码查询用户")
    @GetMapping("/check/{name}&{password}")
    public UserBean getUserByNameAndPwd(@PathVariable("name") String name ,@PathVariable("password") String password){
        UserBean userBean = null;
        for (UserBean user : userBeans){
            if (user.getName().equals(name) && user.getPassword().equals(password)){
                userBean = user;
            }
        }
        return userBean;
    }

    @ApiOperation("查询所有用户")
    @GetMapping("/userAll")
    public List<UserBean> getUserAll(){
        return userBeans;
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    public List<UserBean> addUserByParams(@RequestBody UserBean userBean){
        List<UserBean> userBeans1 = userBeans;
        List users = new ArrayList(userBeans1);
        users.add(userBean);
        return users;
    }

    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public List<UserBean> deleteUserByParams(@RequestBody UserBean userBean){
        users.remove(userBean);
        return users;
    }

    @ApiOperation("登录")
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String name,String password){
        logger.info(name + ": " + password);
        UserBean userBean = getUserByNameAndPwd(name, password);
        if(userBean!=null){
            return "success";
        }else {
            return "error";
        }
    }
}