package com.hull.controller;

import com.hull.entity.User;
import com.hull.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ModelAndView view = new ModelAndView("user/userInfo");
        view.addObject("user",user);
        return view;
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public Object addUser(){
        return new ModelAndView("user/addUser");
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public Object saveUser(User user){
        logger.info("insert user .. ");
        Map<String,Object> map = new HashMap<>();
        int n = userService.insertSelective(user);
        if (n>0){
            return  "redirect:/user/userList";
        }else{
            return  "redirect:/user/addUser";
        }
    }

    @RequestMapping("/userList")
    public Object userList(User user){
        logger.info("insert user .. ");
        List<User> userList = userService.selectAll(user);
        ModelAndView view = new ModelAndView("user/userList");
        view.addObject("userList",userList);
        return view;
    }

    @RequestMapping(value = "/delUser")
    public Object delUser(String userId){
        logger.info("delete user  "+userId);
        userService.deleteByPrimaryKey(userId);
        return  "redirect:/user/userList";
    }


}
