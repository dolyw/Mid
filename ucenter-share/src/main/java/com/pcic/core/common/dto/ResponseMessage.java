package com.pcic.core.common.dto;

import com.pcic.enums.HttpCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ResponseMessage
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 15:26
 */
@Data
@Schema(description = "统一返回结果")
public class ResponseMessage<T> {

    /** 响应码 */
	@Schema(description = "响应码 0000-处理成功 9999-系统异常")
    private String code;

    /** 响应信息 */
	@Schema(description = "响应信息")
    private String message;

	/** 响应内容 */
	@Schema(description = "响应内容")
    private T data;

    public ResponseMessage() {}

	public ResponseMessage(String code, String message, T data) {
    	this.code = code;
    	this.message = message;
    	this.data = data;
	}

	public static <T> ResponseMessage<T> success() {
		return success(HttpCodeEnum.SUCCESS.getName(), null);
	}

	public static <T> ResponseMessage<T> success(T data) {
		return success(HttpCodeEnum.SUCCESS.getName(), data);
	}

	public static <T> ResponseMessage<T> success(String message) {
		return success(message, null);
	}

	/**
	 * 处理成功
	 *
	 * @param message
	 * @param data
	 * @return com.pcic.app.common.dto.ResponseMessage<T>
	 * @throws
	 * @author wliduo[i@dolyw.com]
	 * @date 2021/8/23 21:03
	 */
	public static <T> ResponseMessage<T> success(String message, T data) {
    	return new ResponseMessage<>(HttpCodeEnum.SUCCESS.getCode(), message, data);
	}

	public static <T> ResponseMessage<T> fail() {
		return fail(HttpCodeEnum.FAIL.getName(), null);
	}

	public static <T> ResponseMessage<T> fail(T data) {
		return fail(HttpCodeEnum.FAIL.getName(), data);
	}

	public static <T> ResponseMessage<T> fail(String message) {
		return fail(message, null);
	}

	/**
	 * 处理失败
	 *
	 * @param message
	 * @param data
	 * @return com.pcic.app.common.dto.ResponseMessage<T>
	 * @throws
	 * @author wliduo[i@dolyw.com]
	 * @date 2021/8/23 21:03
	 */
	public static <T> ResponseMessage<T> fail(String message, T data) {
		return new ResponseMessage<>(HttpCodeEnum.FAIL.getCode(), message, data);
	}

	/**
	 * 自定义返回
	 *
	 * @param code
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> custom(String code, String message) {
		return custom(code, message, null);
	}

	/**
	 * 自定义返回
	 *
	 * @param code
	 * @param message
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> custom(String code, String message, T data) {
		return new ResponseMessage<>(code, message, data);
	}
	
}
