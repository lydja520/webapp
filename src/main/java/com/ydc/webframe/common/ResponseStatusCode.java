package com.ydc.webframe.common;

import java.io.Serializable;

public class ResponseStatusCode implements Serializable, Cloneable {
    /**
     * 正常返回
     */
    public final static int SUCCESS_CODE = 200;
    /**
     * 系统内部错误
     */
    public final static int SYSTEM_ERROR_CODE = 500;
    /**
     * 页面未找到
     */
    public final static int PAGE_NOT_FOUND_CODE = 404;
    /**
     * 未授权
     */
    public final static int UNAUTHORIZED = 401;

}
