package com.bupt.battery.handler;

import com.bupt.battery.entity.baseEntity.ResponseResult;
import com.bupt.battery.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
    private static final String errMsg="handleException";

    public ResponseResult handleException(MyException e)
    {
        return ResponseResult.of(e.getErrCode()).setMsg(errMsg);
    }
}
