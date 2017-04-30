package com.ydc.webapp.dao.impl;

import com.ydc.webapp.bean.User;
import com.ydc.webapp.dao.IUserDao;
import com.ydc.webframe.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by ydc on 17-4-16.
 */
@Repository(value = "userDao")
public class UserDao extends BaseDao<User> implements IUserDao{
}
