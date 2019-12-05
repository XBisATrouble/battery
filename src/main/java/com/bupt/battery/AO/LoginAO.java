package com.bupt.battery.AO;

import java.io.Serializable;
import lombok.Data;

@Data
public class LoginAO implements Serializable {
    private Boolean flag;
    private String msg;
}
