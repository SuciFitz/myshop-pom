package com.sucifitz.myshop.web.admin.service;

import com.sucifitz.myshop.commons.dto.BaseResult;
import com.sucifitz.myshop.domain.TbUser;

import java.util.List;

/**
 * @author Sucifitz
**/
public interface TbUserService {

    /**
     * 查询全部用户信息
     *
     * @return 用户列表
     */
    List<TbUser> selectAll();

    /**
     * 新增
     *
     * @param tbUser tbUser实体
     */
    BaseResult save(TbUser tbUser);

    /**
     * 删除
     *
     * @param id 用户id
     */
    void delete(Long id);

    /**
     * 根据id查询
     *
     * @param id 用户id
     * @return tbUser实体
     */
    TbUser getById(Long id);

    /**
     * 更新
     *
     * @param tbUser tbUser实体
     */
    void update(TbUser tbUser);

    /**
     * 根据用户名模糊查询
     *
     * @param username 用户名
     * @return tbUser实体
     */
    List<TbUser> selectByName(String username);

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return tbUser实体
     */
    TbUser login(String email, String password);

    /**
     * 搜索
     *
     * @param tbUser tbUser实体
     * @return tbUser实体
     * @date 2020/4/15 22:15
     */
    List<TbUser> search(TbUser tbUser);

    /**
     * 批量删除
     *
     * @param ids id数组
     * @date 2020/5/7 21:20
     */
    void deleteMulti(String[] ids);

    /**
     * 分页
     * @param start 起始位置
     * @param length 长度
     * @return  用户列表
     */
    List<TbUser> pagination(int start, int length);

    /**
     * 查询用户数量
     * @return 用户数量
     */
    int userCount();
}
