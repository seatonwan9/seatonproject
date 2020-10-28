package com.xearth.sp.seatonproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangxudong
 * @date 2020/5/15 14:28
 */
@Controller
@RequestMapping("/arcgis")
public class MapController {

    @RequestMapping("/map")
    public String map() {
        return "map";
    }
}
