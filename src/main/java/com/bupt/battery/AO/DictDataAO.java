package com.bupt.battery.AO;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class DictDataAO implements Serializable {
    String attributeName;
    List<String> attributeValue;
 }
