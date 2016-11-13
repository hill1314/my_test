package com.hull.controller;

import com.hull.entity.User;
import com.hull.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    final static Logger logger = Logger.getLogger(UserController.class);
    @Resource
    UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public Object getUser(String userId){
        logger.info("userId=="+userId);
        User user = userService.selectByPrimaryKey(userId);
        ModelAndView view = new ModelAndView("userInfo");
        view.addObject("user",user);
        return view;
    }

    @RequestMapping("/saveUser")
    public Object saveUser(User user){
        logger.info("insert user .. ");
        int n = userService.insertSelective(user);
        ModelAndView view = new ModelAndView("userList");
        return view;
    }

    @RequestMapping("/userList")
    public Object userList(User user){
        logger.info("insert user .. ");
        List<User> userList = userService.selectAll(user);
        ModelAndView view = new ModelAndView("userList");
        view.addObject("userList",userList);
        return view;
    }



}
