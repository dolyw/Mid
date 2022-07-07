package com.pcic.config;

import com.pcic.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @author wliduo
 * @date 2019/2/15 14:36
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;

    @Value("${threadPool.maxPoolSize}")
    private int maxPoolSize;

    @Value("${threadPool.queueCapacity}")
    private int queueCapacity;

    @Value("${threadPool.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Value("${threadPool.ioThreadNamePrefix}")
    private String ioThreadNamePrefix;

    @Value("${threadPool.cpuThreadNamePrefix}")
    private String cpuThreadNamePrefix;

    /**
     * IO密集型线程池配置，请求需快速响应异步线程使用，响应速度优先
     *
     * @param
     * @return java.util.concurrent.Executor
     * @author wliduo
     * @date 2019/2/15 14:44
     */
    @Bean(name = Constants.THREAD_POOL_IO)
    public ThreadPoolExecutor ioThreadPoolExecutor() {
        log.info("---------- IoThreadPool Start ----------");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds, TimeUnit.SECONDS,
                // 同步队列，没有任务排队
                new SynchronousQueue<>(false),
                new CustomizableThreadFactory(ioThreadNamePrefix),
                // 到达最大线程直接拒绝
                new ThreadPoolExecutor.AbortPolicy());
        log.info("---------- IoThreadPool End ----------");
        return threadPoolExecutor;
    }

    /**
     * CPU(计算)密集型线程池配置，后台定时任务使用，吞吐量优先
     *
     * @param
     * @return java.util.concurrent.Executor
     * @author wliduo
     * @date 2019/2/15 14:44
     */
    @Bean(name = Constants.THREAD_POOL_CPU)
    public ThreadPoolExecutor cpuThreadPoolExecutor() {
        log.info("---------- CpuThreadPool Start ----------");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveSeconds, TimeUnit.SECONDS,
                // 后台任务排队处理
                new LinkedBlockingQueue<>(queueCapacity),
                new CustomizableThreadFactory(cpuThreadNamePrefix),
                // 到达最大线程用调用者所在的线程来执行任务
                new ThreadPoolExecutor.CallerRunsPolicy());
        log.info("---------- CpuThreadPool End ----------");
        return threadPoolExecutor;
    }

}
