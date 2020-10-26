package com.xearth.sp.seatonproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
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
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 排序查询所有用户
     * @return
     */
    @GetMapping(value = "/findAll")
    public List<User> findAll() {
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
    public Page<User> findAllByPage(Integer page, Integer size, HttpServletResponse response) {
        //处理跨域请求
        response.setHeader("Access-Control-Allow-Origin","*");

        if(page == null || page <= 0) {
            page = 0;
        }else {
            page -= 1;
        }
        if(size == null) {
            size = 5;
        }
        return  userService.findAllByPage(page, size);
    }

    /**
     * 插入用户
     */
    @PostMapping(value = "/insertUser")
    public void insertUser() {
        User user = new User();
        user.setName("wxd");
        user.setAge(24);
        user.setdatetime(new Date());
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
            user.setName("wxd" + i);
            user.setAge(24);
            user.setdatetime(new Date());
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
            user.setName("wxd" + i);
            user.setAge(24);
            user.setdatetime(new Date());
            userList.add(user);
        }
        userService.batchInsert(userList);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("batchInsert耗时：" + time);
    }

    /**
     * 多线程计算测试
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* 多线程start */
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
        /* 多线程end */

        /*----------------------------------------------------------------------------*/

        /* 单线程start */
        /*Long startTime = System.currentTimeMillis();
        int sum = 0;
        MyCallable2 c = new MyCallable2(5000, 2000);
        sum = c.call();
        System.out.println("sum=" + sum);
        MyCallable2 c2 = new MyCallable2(3000, 2000);
        sum = c2.call();
        System.out.println("sum=" + sum);
        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("单线程计算耗时：" + time);*/
        /* 单线程end */
    }

}
