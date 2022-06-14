package com.pcic.api.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pcic.api.validator.example.HandleValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * FeignReq示例
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/5/31 17:59
 */
@Data
public class FeignReq implements Serializable {

    @NotBlank(message = "名称不能为空", groups = { HandleValidator.class })
    @Schema(description = "示例名称")
    private String name;

    @AssertTrue(message = "标识必须为正确", groups = { HandleValidator.class })
    @Schema(description = "示例标识")
    private Boolean mark;

    @Past(message = "日期不能早于当前时间", groups = { HandleValidator.class })
    @Schema(description = "示例时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

}
