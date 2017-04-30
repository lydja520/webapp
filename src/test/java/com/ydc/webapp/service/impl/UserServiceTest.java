package com.ydc.webapp.service.impl;

import com.ydc.webapp.bean.User;
import com.ydc.webapp.service.IUserService;
import com.ydc.webframe.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by ydc on 17-4-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest {

    @Resource(name = "userService")
    private IUserService userService;

    @Test
    public void add() throws Exception {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("root"+i);
            user.setUserPwd("123456"+i);
            user.setUserNick("芦苇微微"+i);
            user.setBirthday(DateUtils.StringToDate("1992-02-03"));
            user.setUserSex(User.Sex.woman);
            user.setEmail("lydja"+i+"@qq.com");
            this.userService.saveEntity(user);
        }
    }

}