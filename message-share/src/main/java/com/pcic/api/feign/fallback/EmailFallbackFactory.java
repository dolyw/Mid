package com.pcic.api.feign.fallback;

import com.pcic.api.feign.EmailFeignService;
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
public class EmailFallbackFactory implements FallbackFactory<EmailFeignService> {

	@Override
	public EmailFeignService create(Throwable throwable) {
		return new EmailFeignService() {
			@Override
			public String send() {
				log.error("{}", throwable);
				return "";
			}
		};
	}
}
