package com.pcic.core.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 14:23
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 失效标识:  0-有效，1-失效 */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "失效标识")
    private Integer deletedInd;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建人")
    private String createdBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /** 更新人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @OrderBy
    private Date updatedTime;
}
