package com.sucifitz.myshop.web.admin.web.controller;

import com.sucifitz.myshop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Sucifitz
 * @date 2020/6/20 14:45
 */
@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService contentCategoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content_category";
    }
}