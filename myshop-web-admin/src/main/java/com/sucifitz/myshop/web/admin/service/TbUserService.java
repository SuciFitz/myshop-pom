package com.sucifitz.myshop.web.admin.service;

import com.sucifitz.myshop.commons.dto.BaseResult;
import com.sucifitz.myshop.commons.dto.PageInfo;
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
     * @return 保存结果
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
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @param password 密码
     * @return tbUser实体
     */
    TbUser login(String email, String password);

    /**
     * 批量删除
     *
     * @param ids id数组
     * @date 2020/5/7 21:20
     */
    void deleteMulti(String[] ids);

    /**
     * 分页
     *
     * @param draw   长度
     * @param start  起始位置
     * @param length 长度
     * @param tbUser 查询条件
     * @return 用户列表
     */
    PageInfo<TbUser> pagination(int draw, int start, int length, TbUser tbUser);

}
