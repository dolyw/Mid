package com.pcic.api.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * FeignResp示例
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/5/31 17:59
 */
@Data
public class FeignResp {

    private String name;

    private Boolean mark;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

}
