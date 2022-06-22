package com.pcic.core.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * PageQuery
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 17:15
 */
@Data
@Schema(description = "分页对象")
public class PageQuery implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    /**
     * 当前页面数据量
     */
    @Schema(description = "当前页面数据量")
    private int pageSize = 10;

    /**
     * 当前页码
     */
    @Schema(description = "当前页码")
    private int pageNum = 1;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private String field;

    /**
     * 排序规则，asc升序，desc降序
     */
    @Schema(description = "排序规则")
    private String order;
}
