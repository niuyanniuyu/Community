package com.community.controller;

import com.community.entity.User;
import com.community.service.UserService;
import com.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Controller
public class LoginController implements CommunityConstant {
    @Autowired
    private UserService userService;

    // 跳转到注册页面
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {

        return "/site/register";
    }

    // 进行注册
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(Model model, User user) {
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功，我们已经向您的邮箱发送了一封激活邮件！");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));

            return "/site/register";
        }

    }

    // 处理账号激活
    // http://localhost:8080/community/activation/1/code
    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int result = userService.activation(userId, code);
        if (result == ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功，您的账号可以正常使用了！");
            model.addAttribute("target", "/login");
        } else if (result == ACTIVATION_REPEAT) {
            model.addAttribute("msg", "您的账号已经激活，请勿多次激活！");
            model.addAttribute("target", "/index");
        } else if (result==ACTIVATION_FAIL){
            model.addAttribute("msg", "激活失败，您提供的激活码不正确！");
            model.addAttribute("target", "/index");
        }else {
            model.addAttribute("msg", "激活失败，您的账号不存在！");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }

    //
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }


}
