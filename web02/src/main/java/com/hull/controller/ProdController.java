package com.hull.controller;

import com.hull.entity.Product;
import com.hull.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/11/17.
 */

@Controller
@RequestMapping("/prod")
public class ProdController {
    @Resource
    ProductService productService;

    @RequestMapping("/addProd")
    public Object addProd(){
        return "product/addProd";
    }
    @RequestMapping("/saveProd")
    @ResponseBody
    public Object saveProd(Product product){
        int n = productService.insert(product);
        return "success "+n;
    }

}
