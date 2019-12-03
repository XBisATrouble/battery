package com.bupt.battery.form;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class DictForm implements Serializable {
    private String type;
    private String paramName;
    private List<String> paramValue;
}
