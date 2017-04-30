package com.ydc.webframe.dao;

import com.ydc.webframe.util.GenericTypeUtils;
import com.ydc.webframe.common.PageData;
import com.ydc.webframe.common.Pager;
import com.ydc.webframe.exception.AppRuntimeException;
import com.ydc.webframe.hibernate.IQueryParams;
import com.ydc.webframe.util.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by ShenYunjie on 2015/11/16.
 */
public class BaseDao<T> implements IBaseDao<T> {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public final Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public String getTableName() {
        return GenericTypeUtils.getGenerParamType(this.getClass()).getSimpleName();
    }

    @Override
    public String getIDFieldName() {
        Class clazz = GenericTypeUtils.getGenerParamType(this.getClass());
        Method[] methods = clazz.getMethods();
        if (methods == null || methods.length < 1) {
            return null;
        }
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(javax.persistence.Id.class);
            if (annotation == null) {
                continue;
            }
            String methodName = method.getName();
            if (!methodName.startsWith("get")) {
                return null;
            }
            String fieldName = methodName.substring(3);
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
            return fieldName;
        }
        return null;
    }

    @Override
    public T saveEntity(T t) {
        getSession().save(t);
        return t;
    }

    @Override
    public void updateEntity(T t) {
        this.getSession().update(t);
    }

    @Override
    public T saveOrUpdateEntity(T t) {
        this.getSession().saveOrUpdate(t);
        return t;
    }

    @Override
    public void deleteEntity(T t) {
        getSession().delete(t);
    }

    @Override
    public T findEntityById(String id) {
        return (T) getSession().get(GenericTypeUtils.getGenerParamType(this.getClass()), id);
    }

    @Override
    public T loadEntityById(String id) {
        return (T) getSession().load(GenericTypeUtils.getGenerParamType(this.getClass()), id);
    }

   @Override
    public long getRecordTotal(IQueryParams queryParams) {
        StringBuffer hql = new StringBuffer();
        String idField = getIDFieldName();
        if (idField != null) {
            hql.append("select count(").append(idField).append(") from ");
        } else {
            hql.append("select count(*) from ");
        }
        hql.append(getTableName()).append(" ").append(getWhereHqlWithOutSort(queryParams));
        Query query = createQueryWithHQL(hql.toString());
        initQueryWithQueryParams(query, queryParams);
        Object obj = query.uniqueResult();
        Long total = obj == null ? 0L : Long.valueOf(String.valueOf(obj));
        return total;
    }

   @Override
    public long getRecordTotal(String hql, Map<String, Object> queryParams) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        initQueryValues(query, queryParams);
        Object obj = query.uniqueResult();
        Long total = obj == null ? 0L : Long.valueOf(String.valueOf(obj));
        return total;
    }

    @Override
    public int executeUpdate(Map<String, Object> propertyAndValues, IQueryParams queryParams) {
        if (propertyAndValues == null || propertyAndValues.isEmpty()) {
            throw new AppRuntimeException("update param and value not able null");
        }
        StringBuffer hql = new StringBuffer("update ").append(getTableName());
        int index = 0;
        for (String key : propertyAndValues.keySet()) {
            if (StringUtils.isEmpty(key)) {
                throw new AppRuntimeException("update param name not able null");
            }
            if (index == 0) {
                hql.append(" set ");
            } else {
                hql.append(",");
            }
            hql.append(key).append(" = :").append(key);
            index++;
        }
        hql.append(" ").append(getWhereHql(queryParams));
        Query query = createQueryWithHQL(hql.toString(), false);
        initQueryWithQueryParams(query, queryParams);

        initQueryValues(query, propertyAndValues);
        return query.executeUpdate();
    }

    @Override
    public int executeDelete(IQueryParams queryParams) {
        StringBuffer hql = new StringBuffer("delete ").append(getQueryHql(queryParams));
        Query query = createQueryWithHQL(hql.toString(), false);
        initQueryWithQueryParams(query, queryParams);
        return query.executeUpdate();
    }

    @Override
    public List<T> getEntities(IQueryParams queryParams) {
        Query query = createQuery(queryParams);
        initQueryWithQueryParams(query, queryParams);
        return query.list();
    }

    @Override
    public List<T> getEntities(Pager pager, IQueryParams queryParams) {
        Query query = createQuery(queryParams);
        initQueryPage(query, pager);
        initQueryWithQueryParams(query, queryParams);
        return query.list();
    }

    @Override
    public PageData<T> getPageData(Pager pager, IQueryParams queryParams) {
        long total = getRecordTotal(queryParams);
        Query query = createQuery(queryParams);
        initQueryPage(query, pager);
        initQueryWithQueryParams(query, queryParams);
        PageData<T> pageData = new PageData<>(total, pager);
        pageData.setRows(query.list());
        return pageData;
    }

    @Override
    public List<Object[]> getFiledValues(String[] filedNames, Pager pager, IQueryParams queryParams) {
        if (filedNames == null || filedNames.length < 1) {
            throw new AppRuntimeException("select properties not able null");
        }
        StringBuffer hql = new StringBuffer("select ");
        int index = 0;
        for (String selector : filedNames) {
            if (StringUtils.isEmpty(selector)) {
                throw new AppRuntimeException("select params not able null");
            }
            if (index == 0) {
                hql.append(selector);
            } else {
                hql.append(",").append(selector);
            }
            index++;
        }
        hql.append(" ").append(getQueryHql(queryParams));
        Query query = createQueryWithHQL(hql.toString());
        initQueryWithQueryParams(query, queryParams);
        initQueryPage(query, pager);
        return query.list();
    }

    @Override
    public List<Object> getFiledValue(String filedName, Pager pager, IQueryParams queryParams) {
        if (StringUtils.isEmpty(filedName)) {
            throw new AppRuntimeException("select property not able null");
        }
        StringBuffer hql = new StringBuffer("select ").append(filedName).append(" ").append(getQueryHql(queryParams));
        Query query = createQueryWithHQL(hql.toString());
        initQueryWithQueryParams(query, queryParams);
        initQueryPage(query, pager);
        return query.list();
    }

    @Override
    public PageData<Object[]> getPageDataFiledValues(String[] filedNames, Pager pager, IQueryParams queryParams) {
        long total = getRecordTotal(queryParams);
        List<Object[]> result = this.getFiledValues(filedNames, pager, queryParams);
        PageData<Object[]> pageData = new PageData<>(total, pager);
        pageData.setRows(result);
        return pageData;
    }

   @Override
    public PageData<Object> getPageDataFiledValue(String filedName, Pager pager, IQueryParams queryParams) {
        long total = getRecordTotal(queryParams);
        List<Object> result = this.getFiledValue(filedName, pager, queryParams);
        PageData<Object> pageData = new PageData<>(total, pager);
        pageData.setRows(result);
        return pageData;
    }

    /**
     * 根据条件参数创建Query对象，默认不使用二级缓存
     *
     * @param queryParams
     * @return
     */
    private Query createQuery(IQueryParams queryParams) {
        return createQuery(queryParams, isCacheable());
    }

    /**
     * 根据HQL语句创建Query对象,默认不启用二级缓存
     *
     * @param hql
     * @return
     */
    private Query createQueryWithHQL(String hql) {
        return createQueryWithHQL(hql, isCacheable());
    }

    /**
     * 根据条件参数创建Query对象
     *
     * @param queryParams 条件参数
     * @param cacheable   是否启用二级缓存
     * @return
     */
    private Query createQuery(IQueryParams queryParams, boolean cacheable) {
        return createQueryWithHQL(this.getQueryHql(queryParams), cacheable);
    }

    /**
     * 根据HQL语句创建Query对象
     *
     * @param hql
     * @param cacheable 是否启用二级缓存
     * @return
     */
    private Query createQueryWithHQL(String hql, boolean cacheable) {
        Query query = getSession().createQuery(hql);
        if (cacheable) {
            query.setCacheable(cacheable);
        }
        return query;
    }

    /**
     * 根据条件参数创建Query对象，不包括排序语句不启用二级缓存
     *
     * @param queryParams 条件参数
     * @return
     */
    private Query createQueryWithoutSort(IQueryParams queryParams) {
        return createQueryWithoutSort(queryParams, isCacheable());
    }

    /**
     * 根据条件参数创建Query对象，不包括排序语句
     *
     * @param queryParams 条件参数
     * @param cacheable   是否启用二级缓存
     * @return
     */
    private Query createQueryWithoutSort(IQueryParams queryParams, boolean cacheable) {
        return createQueryWithHQL(this.getQueryHqlWithOutSort(queryParams), cacheable);
    }

    /**
     * 根据Map对象初始化Query对象的值
     *
     * @param query  查询对象
     * @param params "query"对应值
     */
    private void initQueryValues(Query query, Map<String, Object> params) {
        for (String key : params.keySet()) {
            if (StringUtils.isEmpty(key)) {
                throw new AppRuntimeException("hql placeholder not able null");
            }
            query.setParameter(key, params.get(key));
        }
    }

    /**
     * 初始化Query对象的值
     *
     * @param query       Hibernate查询对象
     * @param queryParams 查询条件参数
     */
    private void initQueryWithQueryParams(Query query, IQueryParams queryParams) {
        if (queryParams != null) {
            queryParams.initQueryParamsValue(query);
        }
    }

    /**
     * 初始化Query对象分页
     *
     * @param query
     * @param pager
     */
    private void initQueryPage(Query query, Pager pager) {
        if (pager == null) {
            return;
        }
        query.setFirstResult(pager.getFirstIndex());
        query.setMaxResults(pager.getRows());
    }

    /**
     * 根据查询条件获取对应的包含排序子句的查询HQL语句
     *
     * @param queryParams
     * @return "from xx where 1=1 ... order by ..."
     */
    private String getQueryHql(IQueryParams queryParams) {
        StringBuffer hql = new StringBuffer().append("from ").append(getTableName())
                .append(" ").append(getWhereHql(queryParams));
        System.out.println(hql);
        return hql.toString().trim();
    }

    /**
     * 根据查询条件获取对应的不包含排序的查询HQL语句
     *
     * @param queryParams
     * @return "from xx where 1=1 ..."
     */
    private String getQueryHqlWithOutSort(IQueryParams queryParams) {
        StringBuffer hql = new StringBuffer().append("from ").append(getTableName())
                .append(" ").append(getWhereHqlWithOutSort(queryParams));
        return hql.toString().trim();
    }

    /**
     * 根据查询条件获取不带from关键字，包含排序的HQL语句
     *
     * @param queryParams
     * @return "where 1=1 and ... order by ..."
     */
    private String getWhereHql(IQueryParams queryParams) {
        StringBuffer hql = new StringBuffer().append(" where 1 = 1").append(" ")
                .append(getParamHql(queryParams));
        return hql.toString().trim();
    }

    /**
     * 根据查询条件获取不带from关键字，不包含排序的HQL语句
     *
     * @param queryParams
     * @return "where 1=1 and ..."
     */
    private String getWhereHqlWithOutSort(IQueryParams queryParams) {
        StringBuffer hql = new StringBuffer().append(" where 1 = 1").append(" ")
                .append(getParamHql(queryParams, false));
        return hql.toString().trim();
    }

    /**
     * 获取查询条件对应的包含排序子句的HQL语句
     *
     * @param queryParams
     * @return
     */
    private String getParamHql(IQueryParams queryParams) {
        return getParamHql(queryParams, true);
    }

    /**
     * 获取查询条件对应的HQL语句，对应的HQL语句没有"from"和"where"关键字
     *
     * @param queryParams
     * @param sortable    是否需要排序语句
     * @return
     */
    private String getParamHql(IQueryParams queryParams, boolean sortable) {
        if (queryParams == null) {
            return "";
        }
        if (sortable) {
            return queryParams.getTargetHQL();
        } else {
            return queryParams.getWithOutOrderTargetHQL();
        }
    }

    /**
     * 是否需要启用二级缓存
     *
     * @return
     */
    private boolean isCacheable() {
        Annotation[] annotations = GenericTypeUtils.getGenerParamType(this.getClass()).getAnnotations();
        boolean cacheable = false;
        if (annotations != null) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Cache) {
                    cacheable = true;
                }
            }
        }
        return cacheable;
    }
}
