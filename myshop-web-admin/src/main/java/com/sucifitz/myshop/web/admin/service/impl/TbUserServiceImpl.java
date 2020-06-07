package com.sucifitz.myshop.web.admin.service.impl;

import com.sucifitz.myshop.commons.dto.BaseResult;
import com.sucifitz.myshop.commons.dto.PageInfo;
import com.sucifitz.myshop.commons.utils.RegexpUtils;
import com.sucifitz.myshop.domain.TbUser;
import com.sucifitz.myshop.web.admin.dao.TbUserDao;
import com.sucifitz.myshop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sucifitz
 * @date 2020/2/1 11:14
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);
        // 通过验证
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            if (tbUser.getId() == null) {
                //新增
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            } else {
                //编辑
                tbUserDao.update(tbUser);
            }
            baseResult.setMessage("保存成功");
        }
        return baseResult;
    }

    @Override
    public void delete(Long id) {
        tbUserDao.delete(id);
    }

    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    @Override
    public void update(TbUser tbUser) {
        tbUserDao.update(tbUser);
    }

    @Override
    public List<TbUser> selectByName(String username) {
        return tbUserDao.selectByName(username);
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if (tbUser != null) {
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            // 判断加密后密码和数据库中存放密码是否匹配
            if (md5Password.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public List<TbUser> search(TbUser tbUser) {
        return tbUserDao.search(tbUser);
    }

    @Override
    public void deleteMulti(String[] ids) {
        tbUserDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> pagination(int draw, int start, int length, TbUser tbUser) {
        int count = tbUserDao.userCount(tbUser);
        Map<String, Object> params = new HashMap<>(3);
        params.put("start", start);
        params.put("length", length);
        params.put("tbUser", tbUser);

        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbUserDao.pagination(params));
        return pageInfo;
    }

    @Override
    public int userCount(TbUser tbUser) {
        return tbUserDao.userCount(tbUser);
    }

    /**
     * 用户信息有效性验证
     *
     * @param tbUser 用户实体
     * @author Sucifitz
     * @date 2020/3/19 23:51
     **/
    private BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success("新增成功");
        // 非空验证
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
        } else if (RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱格式不正确，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机号不能为空，请重新输入");
        } else if (RegexpUtils.checkPhone(tbUser.getPhone())) {
            baseResult = BaseResult.fail("手机号格式不正确，请重新输入");
        }
        return baseResult;
    }
}