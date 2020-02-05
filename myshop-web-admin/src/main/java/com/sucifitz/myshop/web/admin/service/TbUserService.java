package com.sucifitz.myshop.web.admin.service;

import com.sucifitz.myshop.domain.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Sucifitz
**/
public interface TbUserService {

    /**
     * @Description: 查询全部用户信息
     * @Param: []
     * @Return: java.util.List<com.sucifitz.myshop.domain.TbUser>
     **/
    List<TbUser> selectAll();

    /**
     * @Description: 新增用户
     * @Param: [tbUser]
     * @Return: void
     **/
    void insert(TbUser tbUser);

    /**
     * @Description: 删除
     * @Param: [id]
     * @Return: void
     **/
    void delete(Long id);

    /**
     * @Description: 根据id查询
     * @Param: [id]
     * @Return: com.sucifitz.myshop.domain.TbUser
     **/
    TbUser getById(Long id);

    /**
     * @Description: 更新
     * @Param: [tbUser]
     * @Return: void
     **/
    void update(TbUser tbUser);

    /**
     * @Description: 根据用户名模糊查询
     * @Param: [username]
     * @Return: java.util.List<com.sucifitz.myshop.domain.TbUser>
     **/
    List<TbUser> selectByName(String username);

    /**
     * @Description: 用户登录
     * @Param: [email, password]
     * @Return: com.sucifitz.myshop.domain.TbUser
     **/
    TbUser login(String email, String password);
}
