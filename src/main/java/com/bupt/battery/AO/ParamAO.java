package com.bupt.battery.AO;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class ParamAO implements Serializable {
    String paramName;
    String paramType;
    String paramValue;
}
