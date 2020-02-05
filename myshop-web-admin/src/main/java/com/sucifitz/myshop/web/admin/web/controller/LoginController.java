/**
 * FileName: LoginController
 * Author:   Sucifitz
 * Date:     2019/6/21 22:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sucifitz.myshop.web.admin.web.controller;

import com.sucifitz.myshop.domain.TbUser;
import com.sucifitz.myshop.domain.User;
import com.sucifitz.myshop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Sucifitz
 * @create 2019/6/21
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     *
     * @Author: Sucifitz
     * @Description: 登录跳转
     * @Date: 2020/1/5 16:09
     * @Param: []
     * @Return: java.lang.String
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
     *
     * @Author: Sucifitz
     * @Description: 登录逻辑
     * @Date: 2020/1/5 16:09
     * @Param: [email, password]
     * @Return: java.lang.String
     **/
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password,
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

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return login(request);
    }
}