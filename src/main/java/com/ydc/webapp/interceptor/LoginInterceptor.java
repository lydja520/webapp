package com.ydc.webapp.interceptor;

import com.ydc.webframe.controller.BaseController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by YangDaiChun on 2015/12/1.
 */
public class LoginInterceptor extends BaseController implements HandlerInterceptor {

    private List<String> unCheckUriList;

    public List<String> getUnCheckUriList() {
        return unCheckUriList;
    }

    public void setUnCheckUriList(List<String> unCheckUriList) {
        this.unCheckUriList = unCheckUriList;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        String requestUri = httpServletRequest.getRequestURI();
//        for (String uri : unCheckUriList) {
//            if (requestUri.endsWith(uri)) {
//                return true;
//            }
//        }
//        if (this.getCurrentAdmin(httpServletRequest) != null) {
//            return true;
//        } else {
//            String contextPath = httpServletRequest.getContextPath();
//            httpServletResponse.sendRedirect(contextPath + "/admin/login");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
