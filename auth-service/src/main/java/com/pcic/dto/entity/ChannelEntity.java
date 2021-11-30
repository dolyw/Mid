package com.pcic.dto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pcic.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * ChannelEntity
 *
 * @author Generator
 * @date 2021-11-30 10:32:34
 */
public class ChannelEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Long channelId;

    /** 渠道代码 */
    @ApiModelProperty(value = "渠道代码")
    private String channelCode;

    /** 渠道名称 */
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    /** 渠道类型 */
    @ApiModelProperty(value = "渠道类型")
    private Integer channelType;

    /** 渠道状态：1-待审核 2-审核通过 3-合作中 4-暂停合作 5-废弃 */
    @ApiModelProperty(value = "渠道状态：1-待审核 2-审核通过 3-合作中 4-暂停合作 5-废弃")
    private Integer status;

    /** 渠道生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "渠道生效日期")
    private Date effectiveDate;

    /** 渠道失效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "渠道失效日期")
    private Date invalidDate;

    /** 渠道联系人 */
    @ApiModelProperty(value = "渠道联系人")
    private String channelContactName;

    /** 渠道联系电话 */
    @ApiModelProperty(value = "渠道联系电话")
    private String channelContactPhone;

    /** 渠道联系邮箱 */
    @ApiModelProperty(value = "渠道联系邮箱")
    private String channelContactEmail;

    /**
     * 获取属性主键ID的值
     */
    public Long getChannelId() {
        return this.channelId;
    }

    /**
     * 设置属性主键ID的值
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取属性渠道代码的值
     */
    public String getChannelCode() {
        return this.channelCode;
    }

    /**
     * 设置属性渠道代码的值
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 获取属性渠道名称的值
     */
    public String getChannelName() {
        return this.channelName;
    }

    /**
     * 设置属性渠道名称的值
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取属性渠道类型的值
     */
    public Integer getChannelType() {
        return this.channelType;
    }

    /**
     * 设置属性渠道类型的值
     */
    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    /**
     * 获取属性渠道状态：1-待审核 2-审核通过 3-合作中 4-暂停合作 5-废弃的值
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置属性渠道状态：1-待审核 2-审核通过 3-合作中 4-暂停合作 5-废弃的值
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取属性渠道生效日期的值
     */
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    /**
     * 设置属性渠道生效日期的值
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 获取属性渠道失效日期的值
     */
    public Date getInvalidDate() {
        return this.invalidDate;
    }

    /**
     * 设置属性渠道失效日期的值
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    /**
     * 获取属性渠道联系人的值
     */
    public String getChannelContactName() {
        return this.channelContactName;
    }

    /**
     * 设置属性渠道联系人的值
     */
    public void setChannelContactName(String channelContactName) {
        this.channelContactName = channelContactName;
    }

    /**
     * 获取属性渠道联系电话的值
     */
    public String getChannelContactPhone() {
        return this.channelContactPhone;
    }

    /**
     * 设置属性渠道联系电话的值
     */
    public void setChannelContactPhone(String channelContactPhone) {
        this.channelContactPhone = channelContactPhone;
    }

    /**
     * 获取属性渠道联系邮箱的值
     */
    public String getChannelContactEmail() {
        return this.channelContactEmail;
    }

    /**
     * 设置属性渠道联系邮箱的值
     */
    public void setChannelContactEmail(String channelContactEmail) {
        this.channelContactEmail = channelContactEmail;
    }

}