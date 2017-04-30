package com.ydc.webframe.service;

import com.ydc.webframe.common.PageData;
import com.ydc.webframe.common.Pager;
import com.ydc.webframe.hibernate.IQueryParams;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ydc on 17-4-30.
 */
public interface IBaseService<T> extends Serializable {

    /**
     * 添加一个对象到数据库
     *
     * @param t 需要持久化的对象
     * @return
     */
    T saveEntity(T t);

    /**
     * 根据ID获取持久化对象
     *
     * @param id 对象ID
     * @return
     */
    T findEntityById(String id);

    /**
     * 更新一个持久化对象
     *
     * @param t
     */
    void updateEntity(T t);

    /**
     * 添加或更新，有则更新，无则添加
     *
     * @param t 需要更新或持久化的对象
     * @return
     */
    T saveOrUpdateEntity(T t);

    /**
     * 删除持久化对象
     *
     * @param t 需要删除的持久化对象
     */
    void deleteEntity(T t);

    /**
     * 根据分页参数和查询条件查询持久化对象
     *
     * @param pager       分页参数
     * @param queryParams 查询条件
     * @return
     */
    List<T> getEntities(Pager pager, IQueryParams queryParams);

    /**
     * 根据分页参数和查询条件查询持久化对象
     *
     * @param pager       分页参数
     * @param queryParams 查询条件
     * @return 经过分页的数据
     */
    PageData<T> getPageData(Pager pager, IQueryParams queryParams);
}
