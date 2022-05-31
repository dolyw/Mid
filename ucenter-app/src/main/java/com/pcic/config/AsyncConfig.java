package com.pcic.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * AsyncConfig
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 17:58
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * 这里不实现了，使用 ThreadPoolConfig 里的线程池即可
     *
     * @param
     * @return java.util.concurrent.Executor
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/19 18:00
     */
    /*@Override
    public Executor getAsyncExecutor() {
        return null;
    }*/

    /**
     * 只能捕获无返回值的异步方法，有返回值的被主线程处理
     *
     * @param
     * @return org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/20 10:16
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }

    /***
     * 处理异步方法中未捕获的异常
     *
     * @author wliduo[i@dolyw.com]
     * @date 2020/5/19 19:16
     */
    class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            log.info("Exception message - {}", throwable.getMessage());
            log.info("Method name - {}", method.getName());
            log.info("Parameter values - {}", Arrays.toString(obj));
            if (throwable instanceof Exception) {
                Exception exception = (Exception) throwable;
                log.info("exception:{}", exception.getMessage());
            }
            throwable.printStackTrace();
        }

    }
}
