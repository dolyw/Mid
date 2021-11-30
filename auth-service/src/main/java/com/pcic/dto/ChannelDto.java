package com.pcic.dto;

import com.pcic.dto.entity.ChannelEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

/**
 * ChannelDto
 *
 * @author Generator
 * @date 2021-11-30 10:32:34
 */
@ApiModel(value = "渠道表对象", description = "渠道表对象")
@TableName(value="t_channel")
public class ChannelDto extends ChannelEntity {

    private static final long serialVersionUID = ChannelDto.class.getName().hashCode();

}