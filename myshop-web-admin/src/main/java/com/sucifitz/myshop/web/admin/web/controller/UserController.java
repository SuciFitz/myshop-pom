package com.sucifitz.myshop.web.admin.web.controller;

import com.sucifitz.myshop.commons.dto.BaseResult;
import com.sucifitz.myshop.domain.TbUser;
import com.sucifitz.myshop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 用户管理
 * @author Sucifitz
 * @create 2020/3/15 11:24
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser = null;
        // id不为空，则从数据库获取
        if (id != null) {
            tbUser = tbUserService.getById(id);
        } else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     *
     * @Author: Sucifitz
     * @Description: 跳转到用户列表
     * @Date: 2020/3/19 23:03
     * @Param: [model]
     * @Return: java.lang.String
     **/
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    /**
     *
     * @Author: Sucifitz
     * @Description: 跳转到用户表单页
     * @Date: 2020/3/19 23:04
     * @Param: []
     * @Return: java.lang.String
     **/
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbUserService.save(tbUser);
        // 保存成功
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        } else {
            // 保存失败
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    /**
     *
     * @Author: Sucifitz
     * @Description: 搜索
     * @Date: 2020/4/15 22:21
     * @Param: [keyword, model]
     * @Return: java.lang.String
     **/
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(String keyword, Model model) {
        List<TbUser> tbUsers = tbUserService.search(keyword);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }
}