package com.pcic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pcic.dto.ChannelDto;
import com.pcic.mapper.ChannelMapper;
import com.pcic.service.ChannelService;

/**
 * ChannelServiceImpl
 *
 * @author Generator
 * @date 2021-11-30 10:32:34
 */
@Service("channelService")
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, ChannelDto> implements ChannelService {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);

}