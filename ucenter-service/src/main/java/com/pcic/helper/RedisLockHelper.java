package com.pcic.helper;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * RedisLockHelper
 * https://zhuanlan.zhihu.com/p/128131107
 * https://zhuanlan.zhihu.com/p/130327922
 * https://gitee.com/zlt2000/microservices-platform/tree/master/zlt-commons/zlt-common-core/src/main/java/com/central/common/lock
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/9/15 15:33
 */
@Component
public class RedisLockHelper {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁 - 默认非公平锁
     *
     * @param lockKey
     * @return
     * @throws Exception
     */
    public RLock getLock(String lockKey) throws Exception {
        return getLock(lockKey, false);
    }

    /**
     * 获取锁
     *
     * @param lockKey
     * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
     * @return org.redisson.api.RLock
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 14:47
     */
    public RLock getLock(String lockKey, boolean fair) throws Exception {
        if (StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("分布式锁lockKey为空");
        }
        if (fair) {
            // 当多个客户端线程同时请求加锁时，公平锁优先分配给先发出请求的线程
            return redissonClient.getFairLock(lockKey);
        }
        return redissonClient.getLock(lockKey);
    }

    /**
     * 加锁业务处理 - 默认非公平锁，5S自动释放锁
     *
     * @param lockKey
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 14:47
     */
    public void lock(String lockKey, VoidHandle handle) throws Exception {
        lock(lockKey, false, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 加锁业务处理 - 5S自动释放锁
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 14:47
     */
    public void lock(String lockKey, boolean fair, VoidHandle handle) throws Exception {
        lock(lockKey, fair, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 加锁业务处理 - 默认非公平锁
     *
     * @param lockKey
	 * @param time 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:10
     */
    public void lock(String lockKey, long time, TimeUnit unit, VoidHandle handle) throws Exception {
        lock(lockKey, false, time, unit, handle);
    }

    /**
     * 加锁业务处理
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param time 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:10
     */
    public void lock(String lockKey, boolean fair, long time, TimeUnit unit, VoidHandle handle) throws Exception {
        RLock rLock = getLock(lockKey, fair);
        try {
            rLock.lock(time, unit);
            handle.execute();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 返回值加锁业务处理 - 默认非公平锁，5S自动释放锁
     *
     * @param lockKey
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:10
     */
    public <T> T lock(String lockKey, ReturnHandle<T> handle) throws Exception {
        return lock(lockKey, false, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 返回值加锁业务处理 - 默认5S自动释放锁
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:10
     */
    public <T> T lock(String lockKey, boolean fair, ReturnHandle<T> handle) throws Exception {
        return lock(lockKey, fair, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 返回值加锁业务处理 - 默认非公平锁
     *
     * @param lockKey
	 * @param time 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:10
     */
    public <T> T lock(String lockKey, long time, TimeUnit unit, ReturnHandle<T> handle) throws Exception {
        return lock(lockKey, false, time, unit, handle);
    }

    /**
     * 返回值加锁业务处理
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param time 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:10
     */
    public <T> T lock(String lockKey, boolean fair, long time, TimeUnit unit, ReturnHandle<T> handle) throws Exception {
        RLock rLock = getLock(lockKey, fair);
        try {
            rLock.lock(time, unit);
            return handle.execute();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 尝试获取锁 - 默认非公平锁
     *
     * @param lockKey
     * @return
     * @throws Exception
     */
    public Boolean getTryLock(String lockKey) throws Exception {
        return getTryLock(lockKey, false);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
     * @return java.lang.Boolean
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 16:05
     */
    public Boolean getTryLock(String lockKey, boolean fair) throws Exception {
        if (StringUtils.isEmpty(lockKey)) {
            throw new RuntimeException("分布式锁lockKey为空");
        }
        RLock rLock = null;
        if (fair) {
            // 当多个客户端线程同时请求加锁时，公平锁优先分配给先发出请求的线程
            rLock = redissonClient.getFairLock(lockKey);
        } else {
            rLock = redissonClient.getLock(lockKey);
        }
        return rLock.tryLock();
    }

    /**
     * 尝试加锁业务处理 - 默认非公平锁，3S等待获取锁，5S自动释放锁
     *
     * @param lockKey
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 16:05
     */
    public void tryLock(String lockKey, VoidHandle handle) throws Exception {
        tryLock(lockKey, false, 3, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 尝试加锁业务处理 - 默认3S等待获取锁，5S自动释放锁
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 16:05
     */
    public void tryLock(String lockKey, boolean fair, VoidHandle handle) throws Exception {
        tryLock(lockKey, fair, 3, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 尝试加锁业务处理 - 默认非公平锁
     *
     * @param lockKey
	 * @param wait 等待获取锁时间
	 * @param release 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 16:05
     */
    public void tryLock(String lockKey, long wait, long release, TimeUnit unit, VoidHandle handle) throws Exception {
        tryLock(lockKey, false, wait, release, unit, handle);
    }

    /**
     * 尝试加锁业务处理
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param wait 等待获取锁时间
	 * @param release 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return void
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:40
     */
    public void tryLock(String lockKey, boolean fair, long wait, long release, TimeUnit unit, VoidHandle handle) throws Exception {
        RLock rLock = getLock(lockKey, false);
        if (!rLock.tryLock(wait, release, unit)) {
            throw new RuntimeException("获取锁" + lockKey + "异常");
        }
        try {
            handle.execute();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * 返回值尝试加锁业务处理 - 默认非公平锁，3S等待获取锁，5S自动释放锁
     *
     * @param lockKey
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:40
     */
    public <T> T tryLock(String lockKey, ReturnHandle<T> handle) throws Exception {
        return tryLock(lockKey, false, 3, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 返回值尝试加锁业务处理 - 默认3S等待获取锁，5S自动释放锁
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:40
     */
    public <T> T tryLock(String lockKey, boolean fair, ReturnHandle<T> handle) throws Exception {
        return tryLock(lockKey, fair, 3, 5, TimeUnit.SECONDS, handle);
    }

    /**
     * 返回值尝试加锁业务处理 - 默认非公平锁
     *
     * @param lockKey
	 * @param wait 等待获取锁时间
	 * @param release 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:40
     */
    public <T> T tryLock(String lockKey, long wait, long release, TimeUnit unit, ReturnHandle<T> handle) throws Exception {
        return tryLock(lockKey, false, wait, release, unit, handle);
    }

    /**
     * 返回值尝试加锁业务处理
     *
     * @param lockKey
	 * @param fair 是否公平锁 公平锁会尽量将锁优先分配给先进来的请求
	 * @param wait 等待获取锁时间
	 * @param release 自动释放锁时间(最长锁住时间)
	 * @param unit 时间单位
	 * @param handle 处理业务方法函数
     * @return T
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/2/22 15:51
     */
    public <T> T tryLock(String lockKey, boolean fair, long wait, long release, TimeUnit unit, ReturnHandle<T> handle) throws Exception {
        RLock rLock = getLock(lockKey, false);
        if (!rLock.tryLock(wait, release, unit)) {
            throw new RuntimeException("获取锁" + lockKey + "异常");
        }
        try {
            return handle.execute();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * ReturnHandle
     *
     * @author wliduo[i@dolyw.com]
     * @date 2021/9/15 15:32
     */
    public interface ReturnHandle<T> {

        /**
         * 业务处理
         */
        T execute() throws Exception;

    }

    /**
     * VoidHandle
     *
     * @author wliduo[i@dolyw.com]
     * @date 2021/9/15 15:32
     */
    public interface VoidHandle {

        /**
         * 业务处理
         */
        void execute() throws Exception;

    }

}
