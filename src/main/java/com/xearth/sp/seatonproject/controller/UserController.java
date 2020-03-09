package com.xearth.sp.seatonproject.controller;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 排序查询所有用户
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<User> findAll(){
        List<User> list =  userService.findAll();
        return list;
    }

    /**
     * 分页排序查询用户
     * @param page
     * @param size
     * @param response
     * @return
     */
    @GetMapping("/findAllByPage")
    public Page<User> findAllByPage(Integer page, Integer size, HttpServletResponse response){
        //处理跨域请求
        response.setHeader("Access-Control-Allow-Origin","*");

        if(page == null || page <= 0) {
            page = 0;
        }else {
            page -= 1;
        }
        if(size == null) {
            size = 3;
        }
        return  userService.findAllByPage(page, size);
    }

}
