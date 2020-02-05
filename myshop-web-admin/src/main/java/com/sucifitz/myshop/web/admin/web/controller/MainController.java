/**
 * FileName: MainController
 * Author:   Sucifitz
 * Date:     2020/1/5 16:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.sucifitz.myshop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Sucifitz
 * @create 2020/1/5
 * @since 1.0.0
 */
@Controller
public class MainController {

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}