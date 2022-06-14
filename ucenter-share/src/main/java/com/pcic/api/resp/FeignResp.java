package com.pcic.api.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * FeignResp示例
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/5/31 17:59
 */
@Data
public class FeignResp implements Serializable {

    @Schema(description = "示例名称")
    private String name;

    @Schema(description = "示例标识")
    private Boolean mark;

    @Schema(description = "示例时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

}
