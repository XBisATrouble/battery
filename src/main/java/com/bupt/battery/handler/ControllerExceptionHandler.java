package com.bupt.battery.handler;

import com.bupt.battery.entity.baseEntity.ResponseResult;
import com.bupt.battery.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
    private static final String errMsg="handleException";
    @ExceptionHandler(MyException.class)
    public ResponseResult handleException(MyException e)
    {
        return ResponseResult.of(e.getErrCode()).setMsg(e.getMessage());
    }
}
