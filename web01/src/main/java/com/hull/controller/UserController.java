package com.hull.controller;

import com.hull.entity.User;
import com.hull.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/7.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    final static Logger logger = Logger.getLogger(UserController.class);

    @Resource
    UserService userService;

    @RequestMapping("/getUser")
    public Object getUser(){
        logger.info("getUser....");
        ModelAndView view = new ModelAndView("userInfo");
        view.addObject("");
        return view;
    }

    @RequestMapping("/saveUser")
    public Object saveUser(){
        logger.info("saveUser....");
        ModelAndView view = new ModelAndView("saveUser");
        User user = new User();
        user.setUserId("001");
        user.setUserName("张三");
        userService.insertSelective(user);
        view.addObject("user",user);
        return view;
    }

}
