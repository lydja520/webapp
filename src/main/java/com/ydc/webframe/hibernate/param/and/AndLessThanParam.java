package com.ydc.webframe.hibernate.param.and;


import com.ydc.webframe.hibernate.param.SimpleParam;

/**
 * 且小于参数类
 * Created by ShenYunjie on 2015/11/13.
 */
public class AndLessThanParam extends SimpleParam {

    /**
     * @param key   属性名称
     * @param value 属性值
     */
    public AndLessThanParam(String key, Object value) {
        super(key, value);
    }

    @Override
    public String getTargetHQL() {
        return LOGIC_AND_KEY + " " + this.key + " < :" + getPlaceholderTitle();
    }
}
