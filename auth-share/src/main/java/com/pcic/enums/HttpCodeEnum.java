package com.pcic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HttpCode
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/6/1 11:40
 */
@Getter
@AllArgsConstructor
public enum HttpCodeEnum {

    /**
     * 处理成功
     */
    SUCCESS("0000", "处理成功"),

    /**
     * 处理失败
     */
    FAIL("9999", "处理失败");

    private String code;

    private String name;

}
