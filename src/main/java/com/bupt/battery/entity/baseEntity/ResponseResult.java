package com.bupt.battery.entity.baseEntity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;


@Data(staticConstructor = "of")
@Accessors(chain = true)
public class ResponseResult<T> {
    @NonNull
    private String code;
    private String msg;
    private T data;
}
