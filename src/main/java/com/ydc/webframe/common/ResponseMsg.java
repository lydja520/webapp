package com.ydc.webframe.common;

import java.io.Serializable;

/**
 * 服务器相应客户端信息类
 * Created by ShenYunjie on 2015/11/18.
 */
public class ResponseMsg implements Serializable, Cloneable {

    private int code;
    private boolean success;
    private String msg;
    private Object extraInfo;

    public ResponseMsg() {
    }

    /**
     * @param code    状态码
     * @param success 操作是否成功
     * @param msg     相应信息
     * @param extraInfo 返回客户端的额外信息
     */
    public ResponseMsg(int code, boolean success, String msg,Object extraInfo) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.extraInfo = extraInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Object extraInfo) {
        this.extraInfo = extraInfo;
    }
}
