package com.pcic.dto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pcic.core.base.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigInteger;

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
    @Schema(description = "主键ID")
    private BigInteger appId;

    /** 应用代码 */
    @Schema(description = "应用代码")
    private String appCode;

    /** 应用名称 */
    @Schema(description = "应用名称")
    private String appName;

    /** 应用私钥 */
    @Schema(description = "应用私钥")
    private String appSecret;

    /** 应用类型 */
    @Schema(description = "应用类型")
    private String appType;

}