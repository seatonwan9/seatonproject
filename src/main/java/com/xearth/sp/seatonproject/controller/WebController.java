package com.xearth.sp.seatonproject.controller;

import com.xearth.sp.seatonproject.service.impl.WebSocketConnect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangxudong
 * @date 2020/10/28 14:03
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    @RequestMapping("test")
    public ModelAndView test(){
        ModelAndView view = new ModelAndView("test");
        return view;
    }

    @GetMapping(value = "send")
    public String send(){
        WebSocketConnect webSocketConnect = new WebSocketConnect();
        webSocketConnect.onMessage("测试");
        return "Success";
    }

}
