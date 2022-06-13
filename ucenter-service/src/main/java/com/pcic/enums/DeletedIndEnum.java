package com.pcic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DeletedIndEnum
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/6/1 11:40
 */
@Getter
@AllArgsConstructor
public enum DeletedIndEnum {

    /**
     * 有效
     */
    VAILD(0, "有效"),

    /**
     * 有效
     */
    INVALID(1, "无效");

    private Integer code;

    private String name;

}
