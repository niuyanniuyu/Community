package com.community.controller;

import com.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    //页面访问Controller层方法Test
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello, Spring Boot";
    }

    //三层架构交互Test
    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    //请求数据响应数据Test
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>请求数据响应数据Test<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 处理GET请求 : /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            //@RequestParam 当传入参数为空时使用
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);

        return "some students";
    }

    // 处理GET请求 : /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    // 处理POST请求 : /student
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);

        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", "30");
        //模板存放在resources/templates下，文件类型不需要写
        mav.setViewName("/demo/view");
        return mav;

    }

    // 响应HTML数据方式2
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "张三");
        model.addAttribute("age", "30");
        //不加@ResponseBody默认返回路径，加了@ResponseBody返回的是字符串
        return "/demo/view";

    }

    // 响应JSON数据 Java对象 -> JSON字符串 -> JS对象

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);

        return emp;
    }

    // 响应多组JSON数据
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        Map<String, Object> emp = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp.put("name", "李四");
        emp.put("age", 23);
        emp.put("salary", 12000.00);
        list.add(emp);

        emp.put("name", "王五");
        emp.put("age", 18);
        emp.put("salary", 18000.00);
        list.add(emp);

        return list;
    }
}

















