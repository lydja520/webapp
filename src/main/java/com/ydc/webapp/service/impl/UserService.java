package com.ydc.webapp.service.impl;

import com.ydc.webapp.bean.User;
import com.ydc.webapp.dao.IUserDao;
import com.ydc.webapp.service.IUserService;
import com.ydc.webframe.common.PageData;
import com.ydc.webframe.common.Pager;
import com.ydc.webframe.hibernate.IQueryParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ydc on 17-4-16.
 */
@Service(value = "userService")
public class UserService implements IUserService{

    @Resource(name = "userDao")
    IUserDao userDao;


    @Override
    public User saveEntity(User user) {
        return this.userDao.saveEntity(user);
    }

    @Override
    public User findEntityById(String id) {
        return null;
    }

    @Override
    public void updateEntity(User user) {

    }

    @Override
    public User saveOrUpdateEntity(User user) {
        return null;
    }

    @Override
    public void deleteEntity(User user) {

    }

    @Override
    public List<User> getEntities(Pager pager, IQueryParams queryParams) {
        return this.userDao.getEntities(pager,queryParams);
    }

    @Override
    public PageData<User> getPageData(Pager pager, IQueryParams queryParams) {
        return null;
    }
}
