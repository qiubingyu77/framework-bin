package com.bin.framework.common.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @autor qiubingyu
 * @ClassName JsonUtil
 * @date 2020/12/20
 **/
public abstract class JsonUtil {

    public static String bean2json(Object bean){
        try(StringWriter stringWriter = new StringWriter()){
            JsonGenerator generator = JsonFactory.builder().build().createGenerator(stringWriter);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(generator,bean);
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2bean(Object bean,Class<T> clazz){
        return null;
    }

    public static void main(String[] args) {
        Map<String,Object> test = Maps.newHashMap();
        test.put("1",1);
        test.put("qby","qby");
        final String s = JsonUtil.bean2json(test);
        System.out.println(s);
    }
}
