package com.pcic.api.feign.fallback;

import com.pcic.api.feign.ExampleFeignService;
import com.pcic.api.req.FeignReq;
import com.pcic.api.resp.FeignResp;
import com.pcic.core.common.dto.RequestMessage;
import com.pcic.core.common.dto.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * 降级处理
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/8/27 14:05
 */
@Slf4j
@Service
public class ExampleFallbackFactory implements FallbackFactory<ExampleFeignService> {

	@Override
	public ExampleFeignService create(Throwable throwable) {
		return new ExampleFeignService() {
			@Override
			public ResponseMessage<FeignResp> handle(RequestMessage<FeignReq> requestMessage) {
				// log.error("{}", throwable);
				return ResponseMessage.fail(throwable.getMessage());
			}

			@Override
			public ResponseMessage info() throws Exception {
				return ResponseMessage.fail(throwable.getMessage());
			}
		};
	}
}
