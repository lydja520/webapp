package com.ydc.webframe.exception;

/**
 * Created by ShenYunjie on 2015/11/16.
 */
public class AppRuntimeException extends RuntimeException {
    public AppRuntimeException() {
        super();
    }

    public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRuntimeException(Throwable cause) {
        super(cause);
    }

    public AppRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
