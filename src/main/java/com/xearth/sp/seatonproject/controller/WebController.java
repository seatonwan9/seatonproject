package com.xearth.sp.seatonproject.controller;

import com.xearth.sp.seatonproject.controller.constructor.Parent;
import com.xearth.sp.seatonproject.controller.constructor.Subparent;
import com.xearth.sp.seatonproject.controller.constructor.Subtine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Stack;

/**
 * @author wangxudong
 * @date 2020/10/28 14:03
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }

    /**
     * 测试构造函数继承及父类super传参
     * @param args
     */
    public static void main(String[] args) {
        Parent parent;
        parent = new Subparent(1);
        System.out.println("-----------------------------------------------------");
        parent = new Subtine(2);
        System.out.println(parent.getNumber());
        System.out.println("-----------------------------------------------------");
        parent = new Subtine();
        System.out.println(parent.getNumber());

        isValid("([])");
    }

    public static boolean isValid(String s) {

        if (s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char c:
             s.toCharArray()) {

            if (c == '(') {
                stack.push(')');
            }else if (c == '[') {
                stack.push(']');
            }else if (c == '{') {
                stack.push('}');
            }else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }

        }
        return stack.isEmpty();
    }

}