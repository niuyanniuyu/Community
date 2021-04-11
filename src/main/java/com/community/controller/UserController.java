package com.community.controller;

import com.community.annotation.LoginRequired;
import com.community.entity.User;
import com.community.service.UserService;
import com.community.util.CommunityUtil;
import com.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    // 跳转设置页面
    @LoginRequired // 自定义注解，需要登录后访问
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    public String getSettingPage() {
        return "/site/setting";
    }

    // 处理上传文件
    @LoginRequired
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if (headerImage == null) {
            model.addAttribute("error", "您还有没选择图片");
            return "/site/setting";
        }
        // 获取文件名及后缀
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "文件格式不正确");
            return "/site/setting";
        }

        // 生成随机文件名
        fileName = CommunityUtil.generateUUID() + suffix;
        // 确定文件存放位置
        File dest = new File(uploadPath + "/" + fileName);
        try {
            // 存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败：" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常", e);
        }

        // 更新当前用户的头像的路径(web路径)
        // http://localhost:8080/community/user/header/xx.png
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header/" + fileName;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }

    // 获取头像
    @RequestMapping(path = "/header/{fileName}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse httpServletResponse) {
        // 服务器存放路径
        fileName = uploadPath + "/" + fileName;
        // 文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 响应图片
        httpServletResponse.setContentType("image/" + suffix);

        // fis和os在编译时自动加上finally中的close方法
        try (
                FileInputStream fis = new FileInputStream(fileName);
                OutputStream os = httpServletResponse.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            // 游标
            int b = 0;
            // -1表示没有读到数据
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            logger.error("读取头像失败：" + e.getMessage());
        }
    }

    @RequestMapping(path = "/updatePassword", method = RequestMethod.POST)
    public String activation(Model model, String password,String newPassword) {
        // 参数验证
        User user = hostHolder.getUser();
        if (user == null) {
            model.addAttribute("msg", "请登录！");
            return "redirect:/index";
        }
        if (StringUtils.isBlank(password)) {
            model.addAttribute("passwordMsg", "请输入原密码！");
            return "/site/setting";
        }
        if (StringUtils.isBlank(newPassword)) {
            model.addAttribute("newPasswordMsg", "请输入新密码！");
            return "/site/setting";
        }

        // 验证旧密码
        if (!userService.identifyPassword(user,password)){
            model.addAttribute("passwordMsg", "原密码不正确！");
            return "/site/setting";
        }

        // 更改新密码
        int result = userService.updatePassword(user,newPassword);
        if (result != 0) {
            model.addAttribute("msg", "密码更改成功！");
            return "redirect:/index";
        } else {
            model.addAttribute("msg", "密码更改失败！");
            return "/site/setting";
        }


    }

}
