package com.pcic.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcic.core.common.dto.PageQuery;
import com.pcic.core.common.dto.ResponseMessage;
import com.pcic.dto.ChannelDto;
import com.pcic.service.ChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ChannelController
 *
 * @author Generator
 * @date 2021-11-30 10:32:34
 */
@Api(tags = "渠道表", description = "渠道表信息操作")
@RestController
@RequestMapping("/channel")
public class ChannelController {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(ChannelController.class);

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    /**
     * 列表
     *
     * @author Generator
     * @date 2021-11-30 10:32:34
     */
    @ApiOperation(value = "渠道表-列表", notes = "渠道表-列表")
    @GetMapping("/list")
    public ResponseMessage<IPage<ChannelDto>> list(PageQuery pageQuery, ChannelDto channelDto) {
        Page<ChannelDto> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        QueryWrapper<ChannelDto> channelDtoQueryWrapper = Wrappers.query(channelDto);
        return ResponseMessage.success(channelService.page(page, channelDtoQueryWrapper));
    }

    /**
     * 查询
     *
     * @author Generator
     * @date 2021-11-30 10:32:34
     */
    @ApiOperation(value = "渠道表-查询", notes = "渠道表-查询")
    @GetMapping("/{id}")
    public ResponseMessage get(@ApiParam(name = "id", value = "渠道表Id", required = true) @PathVariable("id") Long id) {
        return ResponseMessage.success(channelService.getById(id));
    }

    /**
     * 新增
     *
     * @author Generator
     * @date 2021-11-30 10:32:34
     */
    @ApiOperation(value = "渠道表-新增", notes = "渠道表-新增")
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody ChannelDto channelDto) {
        return ResponseMessage.success(channelService.save(channelDto));
    }

    /**
     * 更新
     *
     * @author Generator
     * @date 2021-11-30 10:32:34
     */
    @ApiOperation(value = "渠道表-修改", notes = "渠道表-修改")
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody ChannelDto channelDto) {
        return ResponseMessage.success(channelService.updateById(channelDto));
    }

    /**
     * 删除
     *
     * @author Generator
     * @date 2021-11-30 10:32:34
     */
    @ApiOperation(value = "渠道表-删除", notes = "渠道表-删除")
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage delete(@ApiParam(name = "ids", value = "渠道表Id列表", required = true) @RequestBody List<String> ids) {
        return ResponseMessage.success(channelService.removeByIds(ids));
    }

}