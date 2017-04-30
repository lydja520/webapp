package com.ydc.webframe.hibernate.param.or;

import com.ydc.webframe.hibernate.param.PropertyParam;

/**
 * Created by ShenYunjie on 2016/1/11.
 */
public class OrPropertyParam extends PropertyParam {

    public OrPropertyParam(String key, String value) {
        super(key, value);
    }

    /**
     * 获取此参数值的最终HQL语句
     *
     * @return
     */
    @Override
    public String getTargetHQL() {
        return LOGIC_OR_KEY + " " + getValue();
    }
}
