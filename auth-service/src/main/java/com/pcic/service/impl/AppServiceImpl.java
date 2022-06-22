package com.pcic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.pcic.dto.AppDto;
import com.pcic.mapper.AppMapper;
import com.pcic.service.AppService;

/**
 * AppServiceImpl
 *
 * @author Generator
 * @date 2021-11-30 15:50:45
 */
@Slf4j
@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppMapper, AppDto> implements AppService {

}