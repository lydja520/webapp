package com.ydc.webframe.hibernate.param.or;

import com.ydc.webframe.hibernate.param.NullParam;

/**
 * Created by ShenYunjie on 2015/11/13.
 */
public class OrIsNullParam extends NullParam {

    public OrIsNullParam(String key) {
        super(key);
    }

    @Override
    public String getTargetHQL() {
        return LOGIC_OR_KEY + " " + this.key + " is null";
    }
}
