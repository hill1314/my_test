package com.hull.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/11/7.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUser")
    public Object getUser(){
        return new ModelAndView("userInfo");
    }

}
