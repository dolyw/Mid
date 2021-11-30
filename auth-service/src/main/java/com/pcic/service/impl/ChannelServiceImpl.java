package com.pcic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import com.pcic.dto.ChannelDto;
import com.pcic.mapper.ChannelMapper;
import com.pcic.service.ChannelService;

/**
 * ChannelServiceImpl
 *
 * @author Generator
 * @date 2021-11-30 15:35:17
 */
@Slf4j
@Service("channelService")
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, ChannelDto> implements ChannelService {

}