package com.wangzhihao.blackmarket.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.JSON.toJSONString;

public class DtoUtils {

    private DtoUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static Map toMap(Object dto) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(dto);
            if (value != null) {
                map.put(name, value);
            }
        }
        return map;
    }

    public static String toJsonString(Object dto) throws IllegalAccessException {
        return toJSONString(toMap(dto));
    }
}
