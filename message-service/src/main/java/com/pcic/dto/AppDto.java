package com.pcic.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pcic.dto.entity.AppEntity;
import io.swagger.annotations.ApiModel;

/**
 * AppDto
 *
 * @author Generator
 * @date 2021-11-29 14:58:40
 */
@ApiModel(value = "应用配置表对象", description = "应用配置表对象")
@TableName(value="t_app")
public class AppDto extends AppEntity {

    private static final long serialVersionUID = AppDto.class.getName().hashCode();

}