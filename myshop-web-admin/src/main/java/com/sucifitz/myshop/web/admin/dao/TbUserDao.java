package com.sucifitz.myshop.web.admin.dao;

import com.sucifitz.myshop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Sucifitz
**/
@Repository
public interface TbUserDao {

    /**
     * @Description: 查询全部用户信息
     * @Param: []
     * @Return: java.util.List<com.sucifitz.myshop.domain.TbUser>
     **/
    List<TbUser> selectAll();

    /**
     * @Description: 新增
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
     * @Description: 根据邮箱查询用户
     * @Param: [email]
     * @Return: com.sucifitz.myshop.domain.TbUser
     **/
    TbUser getByEmail(String email);

    /**
     *
     * @Author: Sucifitz
     * @Description: 搜索
     * @Date: 2020/4/15 22:15
     * @Param: [tbUser]
     * @Return: java.util.List<com.sucifitz.myshop.domain.TbUser>
     **/
    List<TbUser> search(TbUser tbUser);
}
