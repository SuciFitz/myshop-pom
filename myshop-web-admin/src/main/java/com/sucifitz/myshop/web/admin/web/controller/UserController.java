package com.sucifitz.myshop.web.admin.web.controller;

import com.sucifitz.myshop.commons.dto.BaseResult;
import com.sucifitz.myshop.domain.TbUser;
import com.sucifitz.myshop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author Sucifitz
 * @date 2020/3/15 11:24
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
     * @return java.lang.String
     * @author Sucifitz
     * 跳转到用户列表
     * @date 2020/3/19 23:03
     * @Param: [model]
     **/
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    /**
     * @return java.lang.String
     * @author Sucifitz
     * 跳转到用户表单页
     * @date 2020/3/19 23:04
     * @Param: []
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
     * @return java.lang.String
     * @author Sucifitz
     * 搜索
     * @date 2020/4/15 22:21
     * @Param: [tbUser, model]
     **/
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(TbUser tbUser, Model model) {
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    /**
     * @return java.lang.String
     * @author Sucifitz
     * 批量删除
     * @date 2020/4/26 22:03
     * @Param: [ids]
     **/
    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotEmpty(ids)) {
            baseResult = BaseResult.success("删除成功");
            String[] idArray = ids.split(",");
            tbUserService.deleteMulti(idArray);
        } else {
            baseResult = BaseResult.fail("删除失败");
        }
        System.out.println(ids);
        return baseResult;
    }

    @ResponseBody
    @RequestMapping(value = "pagination", method = RequestMethod.GET)
    public Map<String, Object> pagination(HttpServletRequest request) {
        Map<String, Object> res = new HashMap<>(16);
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);
        int count = tbUserService.userCount();
        List<TbUser> tbUsers = tbUserService.pagination(start, length);

        // 封装dataTable需要的结果
        res.put("draw", draw);
        res.put("recordsTotal", count);
        res.put("recordsFiltered", count);
        res.put("data", tbUsers);
        res.put("error", "");

        return res;
    }
}