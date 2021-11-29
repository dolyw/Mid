package com.pcic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.pcic.dto.AppDto;
import com.pcic.mapper.AppMapper;
import com.pcic.service.AppService;

/**
 * AppServiceImpl
 *
 * @author Generator
 * @date 2021-11-29 17:28:41
 */
@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppMapper, AppDto> implements AppService {

}