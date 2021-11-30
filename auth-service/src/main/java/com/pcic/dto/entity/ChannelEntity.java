package com.pcic.dto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pcic.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * ChannelEntity
 *
 * @author Generator
 * @date 2021-11-30 10:32:34
 */
@Data
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

}