package com.xearth.sp.seatonproject.controller;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;
import com.xearth.sp.seatonproject.result.ResultData;
import com.xearth.sp.seatonproject.service.RedisService;
import com.xearth.sp.seatonproject.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangxudong
 * @date 2020/3/07 19:35
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户信息", tags = "用户信息接口")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    /**
     * 实体类查询所有用户
     * @return
     */
    @ApiOperation(value = "实体类查询所有用户", notes = "")
    @GetMapping(value = "/findAll")
    public ResultData<List<User>> findAll() {
        List<User> list = userService.findAll();
        return ResultData.data(list);
    }

    /**
     * 投影查询所有用户
     * @return
     */
    @ApiOperation(value = "实体类查询所有用户", notes = "")
    @GetMapping(value = "/findAllUser")
    public ResultData<List<UserProjection>> findAllUser() {
        List<UserProjection> list = userService.findAllUser();
        return ResultData.data(list);
    }

    /**
     * 分页条件查询用户
     * @param page
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页条件查询用户", notes = "传入参数userName，参数可为空")
    @PostMapping("/findUsersByParamByPageable")
    public ResultData<Page<User>> findUsersByParamByPageable(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<User> list = userService.findUsersByParamByPageable(userName, page, size);
        return ResultData.data(list);
    }

    /**
     * 批量插入用户
     */
    @ApiOperation(value = "批量新增", notes = "")
    @PostMapping(value = "/saveAll")
    public ResultData saveAll() {
        Long startTime = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            User user = new User();
//            user.setCompanyId(100001);
            user.setUserName("seaton" + i);
            user.setUserAge(25);
            user.setUserPhone("15101660166");
            userList.add(user);
        }
        userService.saveAll(userList);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("saveAll耗时：" + time);
        return ResultData.success("添加成功");
    }

    /**
     * 批量插入用户自定义
     */
    @ApiOperation(value = "批量新增（优化）", notes = "")
    @PostMapping(value = "/batchInsert")
    public ResultData batchInsert() {
        Long startTime = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            User user = new User();
//            user.setCompanyId(100001);
            user.setUserName("seaton" + i);
            user.setUserAge(25);
            user.setUserPhone("15101660166");
            userList.add(user);
        }
        userService.batchInsert(userList);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("batchInsert耗时：" + time);
        return ResultData.success("添加成功");
    }

    /**
     * Redis缓存对象
     * @return
     */
    @ApiOperation(value = "Redis缓存对象", notes = "")
    @GetMapping(value = "/setCacheData")
    public ResultData setCacheData() {
        List<User> list = (List<User>) findAll();
        for(int i = 0, len = list.size(); i < len; i++) {
            redisService.set("" + list.get(i).getId(), list.get(i));
        }
        return ResultData.success("缓存成功");
    }

    /**
     * 根据key查询Redis缓存对象
     * @param key
     * @return
     */
    @PostMapping(value = "/getCacheData")
    public ResultData getCacheData(@RequestParam String key) {
        return ResultData.data(redisService.get(key));
    }


    @GetMapping(value = "/getUsers")
    public ResultData getUsers() {
        List<User> list = userService.findAll();
        return ResultData.data(list);
    }
}
