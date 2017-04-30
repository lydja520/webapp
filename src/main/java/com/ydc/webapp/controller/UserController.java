package com.ydc.webapp.controller;

import com.ydc.webapp.bean.User;
import com.ydc.webapp.service.IUserService;
import com.ydc.webframe.common.ResponseMsg;
import com.ydc.webframe.controller.BaseController;
import com.ydc.webframe.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ydc on 17-4-16.
 */
@Controller
public class UserController extends BaseController{

    @Resource(name = "userService")
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/saveUsers")
    public ResponseMsg saveUserInfo(){
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
        return this.executeSuccessResult("插入数据成功！");
    }

    @ResponseBody
    @RequestMapping(value = "/user/getUsers")
    public List<User> getUserList(HttpServletRequest request){
        return this.userService.getEntities(this.createPager(request),this.createQueryParams(request));
    }

}
