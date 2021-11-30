package com.pcic.core.common.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * PageQuery
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 17:15
 */
public class PageQuery implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    /**
     * 当前页面数据量
     */
    @ApiModelProperty(value = "当前页面数据量")
    private int pageSize = 10;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private int pageNum = 1;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private String field;

    /**
     * 排序规则，asc升序，desc降序
     */
    @ApiModelProperty(value = "排序规则")
    private String order;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
