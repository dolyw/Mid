package com.pcic.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具
 *
 * @author wliduo[i@dolyw.com]
 */
@Component
@Slf4j
@SuppressWarnings("all")
public class RedisHelper {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> Boolean setCacheObject(String key, T value) {
        return this.set(key, value);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     */
    public <T> Boolean setCacheObject(String key, T value, Long timeout) {
        return this.set(key, value, timeout);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> Boolean setCacheObject(String key, T value, Long timeout, TimeUnit timeUnit) {
        return this.set(key, value, timeout, timeUnit);
    }

    /**
     * 获得缓存的基本对象
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public Long deleteObject(String key) {
        return this.del(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象
     * @return
     */
    public Long deleteObject(Collection collection) {
        return this.del(collection);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public <T> Long setCacheList(String key, List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的List对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(String key) {
        return redisTemplate.opsForList().range(key, 0L, -1L);
    }

    /**
     * 获得缓存的List对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public <T> List<T> getCacheList(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 删除List中的对象
     *
     * @param key
     * @param fields
     */
    public Long deleteCacheList(String key, Long count, Object value) {
        return this.lRem(key, count, value);
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> Long setCacheSetValue(String key, T value) {
        return this.sAdd(key, value);
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public <T> Long setCacheSetValue(String key, Object... value) {
        return this.sAdd(key, value);
    }

    /**
     * 获得缓存的Set
     *
     * @param key
     * @return
     */
    public <T> Set<T> getCacheSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 删除Set中的对象
     *
     * @param key
     * @param fields
     */
    public Long deleteCacheSet(String key, Object... values) {
        return this.sRem(key, values);
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public <T> void setCacheMap(String key, Map<String, T> dataMap) {
        redisTemplate.opsForHash().putAll(key, dataMap);
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public <T> Map<String, T> getCacheMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public <T> Boolean setCacheMapValue(String key, String hKey, T value) {
        return this.hSet(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public <T> T getCacheMapValue(String key, String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的对象
     *
     * @param key
     * @param fields
     */
    public Long deleteCacheMap(String key, Object... hKeys) {
        return this.hDel(key, hKeys);
    }

    /**
     * 缓存ZSet
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     * @param score 缓存的分数
     * @return
     */
    public <T> Boolean setCacheZSetValue(String key, T value, double score) {
        return this.zAdd(key, value, score);
    }

    /**
     * 获得缓存ZSet的数量
     *
     * @param key
     * @return
     */
    public Long getCacheZSetCount(String key) {
        return this.zCard(key);
    }

    /**
     * 获得缓存ZSet，按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start 从0开始，区间左开右闭
     * @param end   传-1表示查询所有
     * @return
     */
    public <T> Set<T> getCacheZSet(String key, long start, long end) {
        return this.zRange(key, start, end);
    }

    /**
     * 获得缓存ZSet，按分数值递增(从大到小)顺序排列
     *
     * @param key
     * @param start 从0开始，区间左开右闭
     * @param end   传-1表示查询所有
     * @return
     */
    public <T> Set<T> getCacheZSetDesc(String key, long start, long end) {
        return this.zRevRange(key, start, end);
    }

    /**
     * 获得缓存ZSet的Score
     *
     * @param key
     * @param value
     * @return
     */
    public Double getCacheZSetScore(String key, Object value) {
        return this.zScore(key, value);
    }

    /**
     * 删除ZSet中的对象
     *
     * @param key
     * @param value
     */
    public Long deleteCacheZSet(String key, Object... value) {
        return this.zRem(key, value);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return Boolean
     */
    public Boolean expire(String key, Long time) {
        return this.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key     Redis键
     * @param time    时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public Boolean expire(String key, Long time, TimeUnit unit) {
        try {
            if (time > 0) {
                return redisTemplate.expire(key, time, unit);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 设置指定的日期过期
     *
     * @param key
     * @param date
     */
    public Boolean expireAt(String key, Date date) {
        try {
            if (date != null) {
                return redisTemplate.expireAt(key, date);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 根据key获取过期时间
     *
     * @param key 键 不能为 null
     * @return 时间(秒) 返回 0代表为永久有效
     */
    public Long ttl(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return -2L;
    }

    /**
     * 判断 key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public Long del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                return redisTemplate.delete(key[0]) ? 1L : 0L;
            } else {
                return redisTemplate.delete(Arrays.asList(key));
            }
        }
        return 0L;
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public Long del(Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public Boolean set(String key, Object value, Long time) {
        return this.set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public Boolean set(String key, Object value, Long time, TimeUnit unit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, unit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 递增
     *
     * @param key 键
     * @return Long
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return Long
     */
    public Long incrBy(String key, Long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key 键
     * @return Long
     */
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几
     * @return Long
     */
    public Long decrBy(String key, Long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * HashGet
     *
     * @param key  键 不能为 null
     * @param item 项 不能为 null
     * @return 值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取 hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public Boolean hMSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public Boolean hMSet(String key, Map<String, Object> map, Long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public Boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public Boolean hSet(String key, String item, Object value, Long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为 null
     * @param item 项 可以使多个不能为 null
     */
    public Long hDel(String key, Object... item) {
        return redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为 null
     * @param item 项 不能为 null
     * @return true 存在 false不存在
     */
    public Boolean hExists(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return Double
     */
    public Double hIncrBy(String key, String item, Double delta) {
        return redisTemplate.opsForHash().increment(key, item, delta);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return Double
     */
    public Long hIncrBy(String key, String item, Long delta) {
        return redisTemplate.opsForHash().increment(key, item, delta);
    }

    /**
     * 根据 key获取 Set中的所有值
     *
     * @param key 键
     * @return Set
     */
    public Set<Object> sMembers(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean sIsMember(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sAdd(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sAdd(String key, Long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return Long
     */
    public Long sCard(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long sRem(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return List
     */
    public List<Object> lRange(String key, Long start, Long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return Long
     */
    public Long lLen(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；
     *              index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return Object
     */
    public Object lIndex(String key, Long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return Boolean
     */
    public Boolean rPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return Boolean
     */
    public Boolean rPush(String key, Object value, Long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return Boolean
     */
    public Boolean rPushAll(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return Boolean
     */
    public Boolean rPushAll(String key, List<Object> value, Long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return Boolean
     */
    public Boolean lSet(String key, Long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long lRem(String key, Long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0L;
        }
    }

    /**
     * 缓存ZSet
     *
     * @param key   缓存键值
     * @param value 缓存的数据
     * @param score 缓存的分数
     * @return
     */
    public <T> Boolean zAdd(String key, T value, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 获得缓存ZSet的数量
     *
     * @param key
     * @return
     */
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 获得缓存ZSet，按分数值递增(从小到大)顺序排列
     *
     * @param key
     * @param start 从0开始，区间左开右闭
     * @param end   传-1表示查询所有
     * @return
     */
    public <T> Set<T> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获得缓存ZSet，按分数值递增(从大到小)顺序排列
     *
     * @param key
     * @param start 从0开始，区间左开右闭
     * @param end   传-1表示查询所有
     * @return
     */
    public <T> Set<T> zRevRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 获得缓存ZSet的Score
     *
     * @param key
     * @param value
     * @return
     */
    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 删除ZSet中的对象
     *
     * @param key
     * @param value
     */
    public Long zRem(String key, Object... value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }
}
