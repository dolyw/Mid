package com.pcic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HttpCode
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/6/1 11:40
 */
@AllArgsConstructor
public enum HttpCodeEnum {

    SUCCESS("0000", "处理成功"),
    FAIL("9999", "处理失败");

    @Getter
    private final String code;

    @Getter
    private final String name;

}
