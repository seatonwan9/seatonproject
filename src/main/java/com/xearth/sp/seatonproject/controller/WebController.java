package com.xearth.sp.seatonproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wangxudong
 * @date 2020/10/28 14:03
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping("/map")
    public String map() {
        return "map";
    }

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 通过stack快速找出是否正确的括号组
     * @param s
     * @return
     */
    public static boolean isValid(String s) {

        if (s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c: s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            }else if (c == '[') {
                stack.push(']');
            }else if (c == '{') {
                stack.push('}');
            }else if (stack.isEmpty() || c != stack.pop()) { // 先进后出，pop栈顶值删除，peek不删除栈顶值
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * FutureTask可用于异步获取执行结果或取消执行任务的场景。通过传入Runnable或者Callable的任务给FutureTask，
     * 直接调用其run方法或者放入线程池执行，之后可以在外部通过FutureTask的get方法异步获取执行结果，
     * 因此，FutureTask非常适合用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
     * 另外，FutureTask还可以确保即使调用了多次run方法，它都只会执行一次Runnable或者Callable任务，
     * 或者通过cancel取消FutureTask的执行等。
     */
    public static void threadCompute() throws ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        CallableDemo c1 = new CallableDemo(1, 1, "线程1");
        CallableDemo c2 = new CallableDemo(2, 2, "线程2");

        Future<String> f1 = threadPool.submit(c1);
        Future<String> f2 = threadPool.submit(c2);
        System.out.println(f1.get()); // 获取异步执行的结果，如果没有结果可用，此方法会阻塞直到异步计算完成
        System.out.println(f2.get());

        Long endTime = System.currentTimeMillis();
        Long time = endTime - startTime;
        System.out.println("多线程计算耗时：" + time);
    }

    /**
     * 测试构造函数继承及父类super传参
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Parent parent;
//        parent = new Subparent(1);
//        System.out.println("-----------------------------------------------------");
//        parent = new Subtine(2);
//        System.out.println(parent.getNumber());
//        System.out.println("-----------------------------------------------------");
//        parent = new Subtine();
//        System.out.println(parent.getNumber());

//        isValid("([])");

        threadCompute();
    }
}