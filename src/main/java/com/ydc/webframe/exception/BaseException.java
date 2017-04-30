//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ydc.webframe.exception;

public class BaseException extends RuntimeException {
    private int code;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseException(){
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(int code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
