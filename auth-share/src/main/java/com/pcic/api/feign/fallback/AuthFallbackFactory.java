package com.pcic.api.feign.fallback;

import com.pcic.api.feign.AuthFeignService;
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
public class AuthFallbackFactory implements FallbackFactory<AuthFeignService> {

	@Override
	public AuthFeignService create(Throwable throwable) {
		return new AuthFeignService() {

			@Override
			public String token() {
				log.error("{}", throwable);
				return "";
			}
		};
	}
}
