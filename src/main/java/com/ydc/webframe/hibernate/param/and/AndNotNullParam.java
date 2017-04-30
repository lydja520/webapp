package com.ydc.webframe.hibernate.param.and;

import com.ydc.webframe.hibernate.param.NullParam;

/**
 * Created by ShenYunjie on 2015/11/13.
 */
public class AndNotNullParam extends NullParam {

    public AndNotNullParam(String key) {
        super(key);
    }

    @Override
    public String getTargetHQL() {
        return LOGIC_AND_KEY + " " + this.key + " is not null";
    }
}
