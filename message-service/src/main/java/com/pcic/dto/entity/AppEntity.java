package com.pcic.dto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pcic.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * AppEntity
 *
 * @author Generator
 * @date 2021-11-29 14:58:40
 */
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

    /**
     * 获取属性主键ID的值
     */
    public Long getAppId() {
        return this.appId;
    }

    /**
     * 设置属性主键ID的值
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 获取属性应用代码的值
     */
    public String getAppCode() {
        return this.appCode;
    }

    /**
     * 设置属性应用代码的值
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    /**
     * 获取属性应用名称的值
     */
    public String getAppName() {
        return this.appName;
    }

    /**
     * 设置属性应用名称的值
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * 获取属性应用私钥的值
     */
    public String getAppSecret() {
        return this.appSecret;
    }

    /**
     * 设置属性应用私钥的值
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * 获取属性应用类型的值
     */
    public String getAppType() {
        return this.appType;
    }

    /**
     * 设置属性应用类型的值
     */
    public void setAppType(String appType) {
        this.appType = appType;
    }

}