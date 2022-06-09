package com.pcic.api.feign;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.pcic.Constants;
import com.pcic.api.req.FeignReq;
import com.pcic.api.resp.FeignResp;
import com.pcic.api.validator.common.RequestMessageValidator;
import com.pcic.api.validator.example.HandleValidator;
import com.pcic.core.common.dto.RequestMessage;
import com.pcic.core.common.dto.ResponseMessage;
import com.pcic.helper.RedisHelper;
import com.pcic.helper.RedisLockHelper;
import com.pcic.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * ExampleFeignServiceImpl
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 13:54
 */
@Slf4j
@RestController
public class ExampleFeignServiceImpl implements ExampleFeignService {

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private RedisLockHelper redisLockHelper;

    @Override
    public ResponseMessage<FeignResp> handle(@RequestBody RequestMessage<FeignReq> requestMessage) {
        // 前置参数检验
        ValidatorUtil.validate(requestMessage, RequestMessageValidator.class, HandleValidator.class);
        // 业务处理
        FeignResp feignResp = new FeignResp();
        BeanUtil.copyProperties(requestMessage.getData(), feignResp);
        return ResponseMessage.success("处理成功", feignResp);
    }

    @Override
    public ResponseMessage info() throws Exception {
        FeignReq feignReq = new FeignReq();
        feignReq.setName("测试Test");
        feignReq.setMark(false);

        redisHelper.set("redisKey", JSON.toJSONString(feignReq), Constants.EXPIRE_MINUTE);
        Object object = redisHelper.get("redisKey");

        redisHelper.setCacheObject("feignReq", feignReq, 60L, TimeUnit.SECONDS);
        FeignReq feignReqTemp = redisHelper.getCacheObject("feignReq");

        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        redisHelper.setCacheList("list", list);
        redisHelper.expire("list", 60L);

        Map<String, Object> map = new HashMap<>(16);
        map.put("hKey","hValue");
        redisHelper.setCacheMap("map", map);
        redisHelper.expire("map", 60L);
        redisHelper.getCacheMapValue("map", "hKey");

        FeignResp feignResp = redisLockHelper.tryLock("keyName", () -> {
            log.info("分布式锁有返回值测试");
            return new FeignResp();
        });
        redisLockHelper.tryLock("keyName", true, () -> {
            log.info("分布式锁无返回值公平锁测试");
        });
        redisLockHelper.tryLock("keyName", 5, 15, TimeUnit.SECONDS, () -> {
            log.info("分布式锁无返回值设定时间测试");
        });
        return ResponseMessage.success(feignResp);
    }
}
