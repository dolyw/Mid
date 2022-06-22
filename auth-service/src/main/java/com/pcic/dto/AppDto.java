package com.pcic.dto;

import com.pcic.dto.entity.AppEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AppDto
 *
 * @author Generator
 * @date 2021-11-30 15:50:45
 */
@Data
@Schema(description = "应用配置表对象")
@TableName(value="t_app")
public class AppDto extends AppEntity {

    private static final long serialVersionUID = AppDto.class.getName().hashCode();

}