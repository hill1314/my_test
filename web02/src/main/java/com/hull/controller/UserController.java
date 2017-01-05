package com.hull.controller;

import com.hull.entity.User;
import com.hull.service.UserService;
import com.hull.utils.JacksonUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.J;

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
            return  "redirect:/user/userListView";
        }else{
            return  "redirect:/user/addUser";
        }
    }

    @RequestMapping("/userListView")
    public Object userListView(){
        return "user/userList";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Object userList(User user){
        logger.info("user list .. ");
        List<User> userList = userService.selectAll(user);
        String jsonStr = "";
        try {
            jsonStr = JacksonUtils.obj2Json(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @RequestMapping(value = "/delUser")
    public Object delUser(String userId){
        logger.info("delete user  "+userId);
        userService.deleteByPrimaryKey(userId);
        return  "redirect:/user/userListView";
    }


}
