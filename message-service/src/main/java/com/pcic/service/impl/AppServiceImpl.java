package com.pcic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pcic.dto.AppDto;
import com.pcic.mapper.AppMapper;
import com.pcic.service.AppService;

/**
 * AppServiceImpl
 *
 * @author Generator
 * @date 2021-11-29 19:31:09
 */
@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppMapper, AppDto> implements AppService {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

}