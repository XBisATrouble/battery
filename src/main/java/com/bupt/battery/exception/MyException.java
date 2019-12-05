package com.bupt.battery.exception;

public class MyException extends Exception {
    private String errCode;

    public MyException(String errCode) {
        this.errCode = errCode;
    }

    public MyException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public MyException(String message, Throwable cause, String errCode) {
        super(message, cause);
        this.errCode = errCode;
    }

    public MyException(Throwable cause, String errCode) {
        super(cause);
        this.errCode = errCode;
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
