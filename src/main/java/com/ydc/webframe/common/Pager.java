package com.ydc.webframe.common;

import java.io.Serializable;

/**
 * 分页实体类
 * Created by ShenYunjie on 2015/11/16.
 *
 * @version 1.0
 */
public class Pager implements Serializable, Cloneable {

    /**
     * 数据分页相关，默认从第一页
     */
    public final static int DEFAULT_PAGE = 1;

    /**
     * 数据分页相关，默认每页数据显示条数（默认每页显示20条数据）
     */
    public final static int DEFAULT_ROWS = 20;

    private Integer page = DEFAULT_PAGE;
    private Integer rows =DEFAULT_ROWS;

    public Pager() {
        super();
    }

    /**
     * @param page 页数
     * @param rows 每页数据条数
     */
    public Pager(Integer page, Integer rows) {
        this();
        this.page = page < 1 ? DEFAULT_PAGE : page;
        this.rows = rows < 0 ? DEFAULT_ROWS : rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * 获取分页查询起始位置
     * @return
     */
    public Integer getFirstIndex() {
        if (page < 1) {
            page = DEFAULT_PAGE;
        }
        if(rows < 0){
            rows = DEFAULT_ROWS;
        }
        return (page - 1) * rows;
    }
}
