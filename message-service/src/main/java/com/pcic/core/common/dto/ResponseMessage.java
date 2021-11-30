package com.pcic.core.common.dto;

import com.pcic.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ResponseMessage
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 15:26
 */
@Data
public class ResponseMessage<T> {

    /** 响应码 */
	@ApiModelProperty(value = "响应码 0000-处理成功 9999-系统异常")
    private String code;

    /** 响应信息 */
	@ApiModelProperty(value = "响应信息")
    private String message;

	/** 响应内容 */
	@ApiModelProperty(value = "响应内容")
    private T data;

    public ResponseMessage() {

	}

	public ResponseMessage(String code, String message, T data) {
    	this.code = code;
    	this.message = message;
    	this.data = data;
	}

	public static <T> ResponseMessage<T> success() {
		return success("操作成功", null);
	}

	public static <T> ResponseMessage<T> success(T data) {
		return success("操作成功", data);
	}

	public static <T> ResponseMessage<T> success(String message) {
		return success(message, null);
	}

	/**
	 * 操作成功
	 *
	 * @param message
	 * @param data
	 * @return com.pcic.app.common.dto.ResponseMessage<T>
	 * @throws
	 * @author wliduo[i@dolyw.com]
	 * @date 2021/8/23 21:03
	 */
	public static <T> ResponseMessage<T> success(String message, T data) {
    	return new ResponseMessage<>(Constants.SUCCESS_CODE, message, data);
	}

	public static <T> ResponseMessage<T> fail() {
		return fail("操作失败", null);
	}

	public static <T> ResponseMessage<T> fail(T data) {
		return fail("操作失败", data);
	}

	public static <T> ResponseMessage<T> fail(String message) {
		return fail(message, null);
	}

	/**
	 * 操作失败
	 *
	 * @param message
	 * @param data
	 * @return com.pcic.app.common.dto.ResponseMessage<T>
	 * @throws
	 * @author wliduo[i@dolyw.com]
	 * @date 2021/8/23 21:03
	 */
	public static <T> ResponseMessage<T> fail(String message, T data) {
		return new ResponseMessage<>(Constants.FAIL_CODE, message, data);
	}


	/**
	 * 自定义操作失败
	 * @param code
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> customFail(String code, String message) {
		return customFail(code, message, null);
	}

	/**
	 * 自定义操作失败实现
	 *@param code
	 * @param message
	 * @param data
	 * @throws
	 */
	public static <T> ResponseMessage<T> customFail(String code, String message, T data) {
		return new ResponseMessage<>(code, message, data);
	}

	/**
	 * 自定义操作成功
	 * @param code
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> customSuccess(String code, String message) {
		return customSuccess(code, message, null);
	}

	/**
	 * 自定义操作成功实现
	 * @param code
	 * @param message
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseMessage<T> customSuccess(String code, String message, T data) {
		return new ResponseMessage<>(code, message, data);
	}
	
}
