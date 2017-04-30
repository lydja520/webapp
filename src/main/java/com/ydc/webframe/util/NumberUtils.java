package com.ydc.webframe.util;

import java.math.BigDecimal;

/**
 * Created by ShenYunjie on 2016/5/11.
 */
public class NumberUtils {

    /**
     * 保存指定位数四舍五入（如果保存位数为空或小于0时默认保存两位小数点）
     *
     * @param digit
     * @param number
     * @return Double
     */
    public static Double doubleScale(Integer digit, double number) {
        if (digit == null || digit < 0) {
            digit = 2;
        }
        BigDecimal decimal = new BigDecimal(number).setScale(digit, BigDecimal.ROUND_HALF_UP);
        double value = decimal.doubleValue();
        return value;
    }

    /**
     * 保存指定位数四舍五入（如果保存位数为空或小于0时默认保存两位小数点）
     *
     * @param digit
     * @param number
     * @return Float
     */
    public static Float floatScale(Integer digit, double number) {
        if (digit == null || digit < 0) {
            digit = 2;
        }
        BigDecimal decimal = new BigDecimal(number).setScale(digit, BigDecimal.ROUND_HALF_UP);
        return decimal.floatValue();
    }
}
