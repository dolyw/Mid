package com.pcic.dto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pcic.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * AppEntity
 *
 * @author Generator
 * @date 2021-11-30 15:50:45
 */
@Data
public class AppEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Long appId;

    /** 应用代码 */
    @ApiModelProperty(value = "应用代码")
    private String appCode;

    /** 应用名称 */
    @ApiModelProperty(value = "应用名称")
    private String appName;

    /** 应用私钥 */
    @ApiModelProperty(value = "应用私钥")
    private String appSecret;

    /** 应用类型 */
    @ApiModelProperty(value = "应用类型")
    private String appType;

}