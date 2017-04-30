package com.ydc.webframe.hibernate;

import com.ydc.webframe.exception.AppRuntimeException;
import com.ydc.webframe.hibernate.common.LikeType;
import com.ydc.webframe.hibernate.common.Order;
import com.ydc.webframe.hibernate.param.IParam;
import com.ydc.webframe.hibernate.param.InParam;
import com.ydc.webframe.util.StringUtils;
import com.sun.istack.internal.NotNull;
import com.ydc.webframe.hibernate.param.and.*;
import com.ydc.webframe.hibernate.param.or.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by ydc on 2015/11/13.
 */
public class GeneralQueryParams implements IQueryParams{

    private static Log log = LogFactory.getLog(GeneralQueryParams.class);
    protected LinkedHashMap<String, IParam> queryParams;
    protected LinkedHashMap<String, Order> sortParams;

    public GeneralQueryParams() {
        this.queryParams = new LinkedHashMap<>();
        this.sortParams = new LinkedHashMap<>();
    }

    @Override
    public IQueryParams andEqual(@NotNull String key, @NotNull Object value) {
        IParam param = new AndEqualParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andNotEq(@NotNull String key, @NotNull Object value) {
        IParam param = new AndNotEqParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andMoreThan(@NotNull String key, @NotNull Object value) {
        IParam param = new AndMoreThanParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andLessThan(@NotNull String key, @NotNull Object value) {
        IParam param = new AndLessThanParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andMoreAndEq(@NotNull String key, @NotNull Object value) {
        IParam param = new AndMoreAndEqParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andLessAndEq(@NotNull String key, @NotNull Object value) {
        IParam param = new AndLessAndEqParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andIsNull(@NotNull String key) {
        IParam param = new AndIsNullParam(key);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andNotNull(@NotNull String key) {
        IParam param = new AndNotNullParam(key);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andBetween(@NotNull String key, @NotNull Object minValue, @NotNull Object maxValue) {
        IParam param = new AndBetweenParam<>(key, minValue, maxValue);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andIn(@NotNull String key, @NotNull Collection<Object> collection) {
        IParam param = new AndInParam(key, collection);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andNotIn(@NotNull String key, @NotNull Collection<Object> collection) {
        IParam param = new AndNotInParam(key, collection);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andRLike(@NotNull String key, String value) {
        IParam param = new AndLikeParam(key, value, LikeType.RIGHT_LIKE);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andLLike(@NotNull String key, String value) {
        IParam param = new AndLikeParam(key, value, LikeType.LEFT_LIKE);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andAllLike(@NotNull String key, String value) {
        IParam param = new AndLikeParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andSubParams(SubQueryParams params) {
        String key = String.valueOf(new Date().getTime());
        IParam param = new AndSubParam(key, params);
        return addParam(key, param);
    }

    @Override
    public IQueryParams andProParam(@NotNull String proParam) {
        String key = String.valueOf(new Date().getTime());
        IParam param = new AndPropertyParam(key, proParam);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orEqual(@NotNull String key, @NotNull Object value) {
        IParam param = new OrEqualParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orNotEq(@NotNull String key, @NotNull Object value) {
        IParam param = new OrNotEqParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orMoreThan(@NotNull String key, @NotNull Object value) {
        IParam param = new OrMoreThanParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orLessThan(@NotNull String key, @NotNull Object value) {
        IParam param = new OrLessThanParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orMoreAndEq(@NotNull String key, @NotNull Object value) {
        IParam param = new OrMoreAndEqParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orLessAndEq(@NotNull String key, @NotNull Object value) {
        IParam param = new OrLessAndEqParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orIsNull(@NotNull String key) {
        IParam param = new OrIsNullParam(key);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orNotNull(@NotNull String key) {
        IParam param = new OrNotNullParam(key);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orBetween(@NotNull String key, @NotNull Object minValue, @NotNull Object maxValue) {
        IParam param = new OrBetweenParam<>(key, minValue, maxValue);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orIn(@NotNull String key, @NotNull Collection<Object> collection) {
        IParam param = new OrInParam(key, collection);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orNotIn(@NotNull String key, @NotNull Collection<Object> collection) {
        IParam param = new OrNotInParam(key, collection);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orRLike(@NotNull String key, String value) {
        IParam param = new OrLikeParam(key, value, LikeType.RIGHT_LIKE);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orLLike(@NotNull String key, String value) {
        IParam param = new OrLikeParam(key, value, LikeType.LEFT_LIKE);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orAllLike(@NotNull String key, String value) {
        IParam param = new OrLikeParam(key, value);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orProParam(@NotNull String proParam) {
        String key = String.valueOf(new Date().getTime());
        IParam param = new OrPropertyParam(key, proParam);
        return addParam(key, param);
    }

    @Override
    public IQueryParams orSubParams(SubQueryParams params) {
        String key = String.valueOf(new Date().getTime());
        IParam param = new OrSubParam(key, params);
        return addParam(key, param);
    }

    @Override
    public IQueryParams sort(@NotNull String key, Order order) {
        if (StringUtils.isEmpty(key)) {
            throw new AppRuntimeException("sort column not able null");
        }
        if (order == Order.DESC) {
            this.sortParams.put(key, order);
        } else
            this.sortParams.put(key, Order.ASC);
        return this;
    }

    @Override
    public IQueryParams addParam(@NotNull String key, IParam param) {
        if (StringUtils.isEmpty(key)) {
            throw new AppRuntimeException("property name not able null");
        }
        if (param instanceof InParam) {
            Collection value = (Collection) param.getValue();
            //value = value == null ? new ArrayList<>() : value;

            if (value == null || value.size() < 1) {
                return this;    //若果In语句中的集合为空或集合数据为空则不加入查询参数
            }
        }
        key = key.trim();
        String keyAlias = key;
        int index = 0;
        while (this.queryParams.containsKey(param.getPlaceholderTitle())) {
            index++;
            keyAlias = key + index;
            param.setKeyAlias(keyAlias);
        }
        if (!key.equals(keyAlias)) {
            param.setKeyAlias(keyAlias);
        }
        queryParams.put(param.getPlaceholderTitle(), param);
        return this;
    }

    @Override
    public void initQueryParamsValue(Query query) {
        for (String key : this.queryParams.keySet()) {
            this.queryParams.get(key).initQueryValues(query);
        }
    }

    @Override
    public String getTargetHQL() {
        StringBuffer hql = new StringBuffer(getWhereHQL());
        hql.append(" ").append(getSortHQL());
        log.debug(hql);
        return hql.toString().trim();
    }

    @Override
    public String getWithOutOrderTargetHQL() {
        return getWhereHQL();
    }

    /**
     * 获取HQL语句的条件部分（不包括排序）
     *
     * @return
     */
    protected String getWhereHQL() {
        StringBuffer hql = new StringBuffer();
        int index = 0;
        for (String key : this.queryParams.keySet()) {
            if (StringUtils.isEmpty(key) || this.queryParams.get(key) == null) {
                throw new AppRuntimeException("Query key or queryParam not able null");
            }
            if (index > 0) {
                hql.append(" ");
            }
            hql.append(this.queryParams.get(key).getTargetHQL());
            index++;
        }
        return hql.toString().trim();
    }

    /**
     * 获取HQL语句的排序部分（不包括条件部分）
     *
     * @return
     */
    protected String getSortHQL() {
        StringBuffer hql = new StringBuffer();
        int index = 0;
        for (String key : this.sortParams.keySet()) {
            if (StringUtils.isEmpty(key)) {
                throw new AppRuntimeException("order by key not able null");
            }
            if (index == 0) {
                hql.append(" order by ").append(key).append(" ").append(this.sortParams.get(key));
            } else {
                hql.append(",").append(key).append(" ").append(this.sortParams.get(key));
            }
            index++;
        }
        return hql.toString().trim();
    }

    @Override
    public boolean queryContainsKey(String key) {
        return this.queryParams.containsKey(key);
    }

    @Override
    public boolean sortContainsKey(String key) {
        return this.sortParams.containsKey(key);
    }
}
