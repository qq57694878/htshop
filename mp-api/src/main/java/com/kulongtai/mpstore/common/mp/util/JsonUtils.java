package com.kulongtai.mpstore.common.mp.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

/**
 * JSON操作工具类
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object o) {
        if (o == null){
            return null;
        }
        String s = null;

        try {
            s = mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }




    public static <T> T parse(String json, Class<T> c) {
        if (StringUtils.hasLength(json) == false){
            return null;
        }
        T t = null;
        try {
            t = mapper.readValue(json, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


}
