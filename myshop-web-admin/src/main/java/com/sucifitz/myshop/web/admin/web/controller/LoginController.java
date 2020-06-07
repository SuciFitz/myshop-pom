package com.sucifitz.myshop.web.admin.web.controller;

import com.sucifitz.myshop.domain.TbUser;
import com.sucifitz.myshop.domain.User;
import com.sucifitz.myshop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Sucifitz
 * @date 2019/6/21
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录跳转
     * @param request http请求
     * @return 如果session有记录，则免密登录
     * @date 2020/1/5 16:09
     **/
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "main";
        }
        return "login";
    }

    /**
     * 登录逻辑
     * @param email 邮箱
	 * @param password 密码
	 * @param httpServletRequest http请求
	 * @param model 返回信息
     * @return 成功则跳转到主页，否则返回错误信息
     * @date 2020/1/5 16:09
     **/
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String email, String password,
                        HttpServletRequest httpServletRequest, Model model) {
        TbUser tbUser = tbUserService.login(email, password);
        // 登陆失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login(httpServletRequest);
        }
        // 登陆成功
        else {
            // 记录登录信息
            httpServletRequest.getSession().setAttribute("user", tbUser);
            return "redirect:/main";
        }
    }

    /**
     * 登出，清除session记录
     * @param request http请求
     * @return 跳转到登录页面
     * @date 2020/6/7 15:11
     **/
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return login(request);
    }
}