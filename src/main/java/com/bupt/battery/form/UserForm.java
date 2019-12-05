package com.bupt.battery.form;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserForm implements Serializable {
    private String username;
    private String password;
    private String role;
}
