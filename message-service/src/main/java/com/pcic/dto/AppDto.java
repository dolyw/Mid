package com.pcic.dto;

import com.pcic.dto.entity.AppEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * AppDto
 *
 * @author Generator
 * @date 2021-11-30 15:50:45
 */
@ApiModel(value = "应用配置表对象", description = "应用配置表对象")
@Data
@TableName(value="t_app")
public class AppDto extends AppEntity {

    private static final long serialVersionUID = AppDto.class.getName().hashCode();

}