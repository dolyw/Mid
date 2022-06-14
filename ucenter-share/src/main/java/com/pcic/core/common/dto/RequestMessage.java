package com.pcic.core.common.dto;

import com.pcic.api.validator.common.RequestMessageValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * RequestMessage
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 15:26
 */
@Data
public class RequestMessage<T> {

    /** 时间戳 */
    @NotBlank(message = "时间戳不能为空", groups = { RequestMessageValidator.class })
    @Schema(description = "时间戳")
    private String requestTime;

    /** 系统标识 */
    @NotBlank(message = "系统标识不能为空", groups = { RequestMessageValidator.class })
    @Schema(description = "系统标识")
    private String systemCode;

    /** 用户Id */
    @Schema(description = "用户Id")
    private String userId;

    /** 业务流水号 **/
    @Schema(description = "业务流水号")
    private String othBusinessNo;

    /** 渠道代码 **/
    @Schema(description = "渠道代码")
    private String channelCode;

    @Valid
    @NotNull(message = "请求内容不能为空", groups = { RequestMessageValidator.class })
    @Schema(description = "请求内容")
    private T data;

    /**
     * 添加Builder模式
     *
     * @author wliduo[i@dolyw.com]
     * @date 2021/8/23 21:03
     */
    public static final class Builder<T> {
        private String requestTime;
        private String systemCode;
        private T data;

        private Builder() {
        }

        public Builder withRequestTime(String requestTime) {
            this.requestTime = requestTime;
            return this;
        }

        public Builder withSystemCode(String systemCode) {
            this.systemCode = systemCode;
            return this;
        }

        public Builder withData(T data) {
            this.data = data;
            return this;
        }

        public RequestMessage<T> build() {
            RequestMessage<T> requestMessage = new RequestMessage<T>();
            requestMessage.setRequestTime(requestTime);
            requestMessage.setSystemCode(systemCode);
            requestMessage.setData(data);
            return requestMessage;
        }
    }
}
