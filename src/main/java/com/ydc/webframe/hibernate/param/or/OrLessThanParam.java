package com.ydc.webframe.hibernate.param.or;


import com.ydc.webframe.hibernate.param.SimpleParam;

/**
 * 或小于参数类
 * Created by ShenYunjie on 2015/11/13.
 */
public class OrLessThanParam extends SimpleParam {

    /**
     * @param key   属性名称
     * @param value 属性值
     */
    public OrLessThanParam(String key, Object value) {
        super(key, value);
    }

    @Override
    public String getTargetHQL() {
        return LOGIC_OR_KEY + " " + this.key + " < :" + getPlaceholderTitle();
    }
}
