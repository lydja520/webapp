package com.ydc.webframe.controller;

import com.ydc.webframe.common.Pager;
import com.ydc.webframe.common.ResponseMsg;
import com.ydc.webframe.common.ResponseStatusCode;

import com.ydc.webframe.exception.BaseException;
import com.ydc.webframe.hibernate.GeneralQueryParams;
import com.ydc.webframe.hibernate.common.LikeType;
import com.ydc.webframe.hibernate.common.Order;

import com.ydc.webframe.util.DateUtils;
import com.ydc.webframe.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制器基类，所有控制器都应继承与此类
 * Created by ShenYunjie on 2015/11/17.
 */
public abstract class BaseController implements Serializable {

    protected final static String PAGE_KEY = "page";
    protected final static String ROWS_KEY = "rows";

    protected final static String SEARCH_NAME_KEY = "searchName";
    protected final static String SEARCH_VALUE_KEY = "searchValue";
    protected final static String SORT_KEY = "sort";
    protected final static String ORDER_KEY = "order";

    protected Log log;

    public BaseController() {
        log = LogFactory.getLog(this.getClass());
    }

    /**
     * @param binder
     */
    @InitBinder
    public final void initBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        simpleDateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
    }

    /**
     * 异常处理
     *
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler
    public ResponseMsg handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Throwable throwable = e.getCause() == null?e:e.getCause();
        this.log.error(e.getMessage(), (Throwable)throwable);
        if(e instanceof BaseException) {
            BaseException exception = (BaseException)e;
            response.setStatus(ResponseStatusCode.SUCCESS_CODE);
            int code = exception.getCode();
            return new ResponseMsg(code == 0 ? 500:code, false, e.getMessage(),null);
        } else {
            response.setStatus(ResponseStatusCode.SYSTEM_ERROR_CODE);
            return new ResponseMsg(ResponseStatusCode.SYSTEM_ERROR_CODE, false,e.getMessage(),null);
        }
    }

    /**
     * 根据一个RequestParam对象创建一个GenralQueryParams对象，并初始化查询对象值，并默认左右匹配
     *
     * @param request
     * @return
     */
    protected GeneralQueryParams createQueryParams(HttpServletRequest request) {
        return createQueryParams(request, LikeType.BOTH_LIKE);
    }

    protected GeneralQueryParams createQueryParams(HttpServletRequest request, GeneralQueryParams queryParams) {
        return createQueryParams(request, queryParams, LikeType.BOTH_LIKE);
    }

    protected GeneralQueryParams createQueryParams(HttpServletRequest request, GeneralQueryParams queryParams, LikeType likeType) {
        if (request == null) {
            return queryParams;
        }
        if (!StringUtils.isEmpty(request.getParameter(SEARCH_NAME_KEY))) {
            switch (likeType) {
                case LEFT_LIKE:
                    queryParams.andLLike(request.getParameter(SEARCH_NAME_KEY), StringUtils.ObjectToString(request.getParameter(SEARCH_VALUE_KEY)));
                    break;
                case RIGHT_LIKE:
                    queryParams.andRLike(request.getParameter(SEARCH_NAME_KEY), StringUtils.ObjectToString(request.getParameter(SEARCH_VALUE_KEY)));
                    break;
                default:
                    queryParams.andAllLike(request.getParameter(SEARCH_NAME_KEY), StringUtils.ObjectToString(request.getParameter(SEARCH_VALUE_KEY)));
            }
        }
        if (!StringUtils.isEmpty(request.getParameter(SORT_KEY))) {
            if (request.getParameter(ORDER_KEY).equalsIgnoreCase(Order.DESC.toString())) {
                queryParams.sort(request.getParameter(SORT_KEY), Order.DESC);
            } else {
                queryParams.sort(request.getParameter(SORT_KEY), Order.ASC);
            }
        }
        return queryParams;

    }

    /**
     * 根据一个RequestParam对象创建一个GenralQueryParams对象，并初始化查询对象值
     *
     * @param request
     * @param likeType 模糊查询模式（左匹配，右匹配，左右匹配）
     * @return
     */
    protected GeneralQueryParams createQueryParams(HttpServletRequest request, LikeType likeType) {
        GeneralQueryParams queryParams = new GeneralQueryParams();

        if (request == null) {
            return queryParams;
        }
        if (!StringUtils.isEmpty(request.getParameter(SEARCH_NAME_KEY))) {
            switch (likeType) {
                case LEFT_LIKE:
                    queryParams.andLLike(request.getParameter(SEARCH_NAME_KEY), StringUtils.ObjectToString(request.getParameter(SEARCH_VALUE_KEY)));
                    break;
                case RIGHT_LIKE:
                    queryParams.andRLike(request.getParameter(SEARCH_NAME_KEY), StringUtils.ObjectToString(request.getParameter(SEARCH_VALUE_KEY)));
                    break;
                default:
                    queryParams.andAllLike(request.getParameter(SEARCH_NAME_KEY), StringUtils.ObjectToString(request.getParameter(SEARCH_VALUE_KEY)));
            }
        }
        if (!StringUtils.isEmpty(request.getParameter(SORT_KEY))) {
            if (request.getParameter(ORDER_KEY).equalsIgnoreCase(Order.DESC.toString())) {
                queryParams.sort(request.getParameter(SORT_KEY), Order.DESC);
            } else {
                queryParams.sort(request.getParameter(SORT_KEY), Order.ASC);
            }
        }
        return queryParams;
    }

    /**
     * 根据用户请求对象创建一个Pager对象
     *
     * @param request
     * @return
     */
    protected Pager createPager(HttpServletRequest request) {
        Pager pager = new Pager();
        if (request == null) {
            return pager;
        }
        try {
            pager.setPage(Integer.parseInt(request.getParameter(PAGE_KEY)));
            pager.setRows(Integer.parseInt(request.getParameter(ROWS_KEY)));
        } catch (Exception e) {
        }

        return pager;
    }

    /**
     * 返回执行成功信息
     * @return {"code":"200", "success":"true", "msg":"操作成功！","extraInfo":null}
     */
    protected ResponseMsg executeSuccessResult() {
        ResponseMsg responseMessage = new ResponseMsg(ResponseStatusCode.SUCCESS_CODE, true, "操作成功！",null);
        return responseMessage;
    }

    /**
     * 返回执行成功信息
     * @param msg 自定义成功信息
     * @return {"code":"200", "success":"true", "msg":"String msg 值","extraInfo":null}
     */
    protected ResponseMsg executeSuccessResult(String msg) {
        ResponseMsg responseMessage = new ResponseMsg(ResponseStatusCode.SUCCESS_CODE, true, msg,null);
        return responseMessage;
    }

    /**
     * 返回执行成功信息
     * @param extraInfo 返回附加信息
     * @return {"code":"200", "success":"true", "msg":"操作成功！","extraInfo":"Object extraInfo"}
     */
    protected ResponseMsg executeSuccessResult(Object extraInfo) {
        ResponseMsg responseMessage = new ResponseMsg(ResponseStatusCode.SUCCESS_CODE, true, "操作成功！", extraInfo);
        return responseMessage;
    }

    /**
     * 返回执行成功信息
     * @param msg 自定义成功信息
     * @return {"code":"200", "success":"true", "msg":"String msg",extraInfo":"Object extraInfo"}
     */
    protected ResponseMsg executeSuccessResult(String msg, Object extraInfo) {
        ResponseMsg responseMsg = new ResponseMsg(ResponseStatusCode.SUCCESS_CODE, true, msg, extraInfo);
        return responseMsg;
    }

}
