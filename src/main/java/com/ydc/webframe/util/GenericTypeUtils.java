package com.ydc.webframe.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型操作类
 * @version 1.0
 */
public class GenericTypeUtils implements Serializable {
    /**
     * 获取泛型参数的类型
     * @param clazz	泛型
     * @return
     */
    public static Class<?> getGenerParamType(Class<?> clazz){
        Type genericType = clazz.getGenericSuperclass();
        if(!(genericType instanceof ParameterizedType)){
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genericType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

    /**
     * 获取类对象的名称
     * @param clazz
     * @return
     */
    public static String getSimpleName(Class<?> clazz){
        return clazz.getSimpleName();
    }
}
