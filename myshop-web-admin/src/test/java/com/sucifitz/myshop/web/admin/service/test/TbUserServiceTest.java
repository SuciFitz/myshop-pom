package com.sucifitz.myshop.web.admin.service.test;

import com.sucifitz.myshop.domain.TbUser;
import com.sucifitz.myshop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Sucifitz
 * @create 2020/2/2 13:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void selectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@admin.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
        tbUser.setPhone("15888888888");
        tbUser.setUsername("sucifitz");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete() {
        tbUserService.delete(38L);
    }

    @Test
    public void testSelect() {
        System.out.println(tbUserService.getById(39L).getUsername());
    }

    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserService.getById(39L);
        tbUser.setUsername("Sucifitz");
        tbUserService.update(tbUser);
    }

    @Test
    public void selectByUserName() {
        List<TbUser> tbUsers = tbUserService.selectByName("uni");
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }
}