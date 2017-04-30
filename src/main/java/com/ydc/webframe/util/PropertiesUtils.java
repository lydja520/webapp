package com.ydc.webframe.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtils
 *
 * @author ydc 2014-09-15
 */
public class PropertiesUtils {

    private static Properties getProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("appConfig.properties");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 获取appConfig.properties中的key的value值
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }

    /**
     * 获取appConfig.properties中的key的value值，若配置文件中无key属性则取defaultValue
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key,String defaultValue){
        return getProperties().getProperty(key,defaultValue);
    }
}
