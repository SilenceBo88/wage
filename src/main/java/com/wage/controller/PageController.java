package com.wage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: zb
 * @Date: Created in 2018/6/22 16:45
 * @Description: 页面跳转
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/{page}")
    public String page(@PathVariable("page") String page){
        return page;
    }
}
