package com.pcic.api.feign.fallback;

import com.pcic.api.feign.XxxFeignService;
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
public class XxxFallbackFactory implements FallbackFactory<XxxFeignService> {

	@Override
	public XxxFeignService create(Throwable throwable) {
		return new XxxFeignService() {
			@Override
			public String handle() {
				log.error("{}", throwable);
				return "";
			}
		};
	}
}
