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
        User user = userService.selectByPrimaryKey("001");
        ModelAndView view = new ModelAndView("userInfo");
        view.addObject("user",user);
        return view;
    }



}
