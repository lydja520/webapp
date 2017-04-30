package com.ydc.webframe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ydc on 17-4-29.
 */
public class PhoneNumUtils {

    /**
     * 判断一个字符串是否为手机号码
     *
     * @param target
     * @return
     */
    public static boolean isMobileNum(String target) {
        final String REGEX_MOBILE = "^(1[3,4,5,7,8][0-9])\\d{8}$";
        Pattern pattern = Pattern.compile(REGEX_MOBILE);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }
}
