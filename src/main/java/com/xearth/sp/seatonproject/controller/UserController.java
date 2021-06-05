package com.xearth.sp.seatonproject.controller;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.UserView;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;
import com.xearth.sp.seatonproject.result.JSONResult;
import com.xearth.sp.seatonproject.service.RedisService;
import com.xearth.sp.seatonproject.service.UserService;
import com.xearth.sp.seatonproject.service.UserViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wangxudong
 * @date 2020/3/07 19:35
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户类", tags = "用户信息接口")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserViewService userViewService;
    @Autowired
    RedisService redisService;

    /**
     * 排序查询所有用户
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        List<User> list = userService.findAll();
        return list;
    }

    /**
     * 投影查询用户
     * @return
     */
    @GetMapping(value = "/findAllUser")
    public List<UserProjection> findAllUser() {
        List<UserProjection> list = userService.findAllUser();
        return list;
    }

    /**
     * 查询用户视图表
     * @return
     */
    @GetMapping(value = "/findAllUserView")
    public List<UserView> findAllUserView() {
        List<UserView> list = userViewService.findAll();
        return list;
    }

    /**
     * 分页条件查询用户
     * @param page
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页条件查询用户", notes = "传入参数userName，参数可为空")
    @PostMapping("/findUsersByUserName")
    public JSONResult findUsersByUserName(@RequestParam(required = false) String userName,
                                          Integer page, Integer size) {
        if (page == null || page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }
        if (size == null) {
            size = 5;
        }
        List<User> userList = userService.findUsersByUserName(userName, page, size);
        return  JSONResult.success(userList);
    }

    /**
     * 插入用户
     */
    @PostMapping(value = "/insertUser")
    public void insertUser() {
        User user = new User();
        user.setUserName("wxd");
        user.setUserAge(18);
        userService.save(user);
    }

    /**
     * 批量插入用户
     */
    @PostMapping(value = "/insertUsers")
    public void insertUsers() {
        Long startTime = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("wxd" + i);
            user.setUserAge(18 + i);
            userList.add(user);
        }
        userService.saveAll(userList);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("saveAll耗时：" + time);
    }

    /**
     * 批量插入用户自定义
     */
    @PostMapping(value = "/batchInsert")
    public void batchInsert() {
        Long startTime = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("wxd" + i);
            user.setUserAge(18 + i);
            userList.add(user);
        }
        userService.batchInsert(userList);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("batchInsert耗时：" + time);
    }

    /**
     * Redis缓存结果
     * @return
     */
    @GetMapping(value = "/setObject")
    public String setObject() {
        List<User> userList = findAll();
        for(int i = 0, len = userList.size(); i < len; i++) {
            redisService.set("" + userList.get(i).getUserId(), userList.get(i));
        }
        return "success";
    }

    /**
     * Redis查询缓存结果
     * @param key
     * @return
     */
    @GetMapping(value = "/getObject")
    public Object getObject(String key) {
        return redisService.get(key);
    }

    /**
     * 多线程计算测试
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        // 创建线程池对象
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        // 创建一个Callable接口子类对象
        MyCallable c = new MyCallable(5000, 2000);
        MyCallable c2 = new MyCallable(3000, 2000);

        // 获取线程池中的线程，调用Callable接口子类对象中的call()方法, 完成求和操作
        // Future<Integer> submit(Callable<Integer> task)
        Future<Integer> result = null;
        Integer sum = 0;

        result = threadPool.submit(c);
        sum = result.get();
        System.out.println("sum=" + sum);

        result = threadPool.submit(c2);
        sum = result.get();
        System.out.println("sum=" + sum);

        //关闭线程池(可以不关闭)

        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("多线程计算耗时：" + time);
    }
}
