package com.ydc.webframe.hibernate.param.and;

import com.ydc.webframe.hibernate.SubQueryParams;
import com.ydc.webframe.hibernate.param.SubParam;
import com.sun.istack.internal.NotNull;

/**
 * Created by ShenYunjie on 2015/11/13.
 */
public class AndSubParam extends SubParam {

    public AndSubParam(String key, @NotNull SubQueryParams subQueryParams) {
        super(key, subQueryParams);
    }

    @Override
    public String getTargetHQL() {
        return getSubParamHQL(LOGIC_AND_FLAG);
    }
}
