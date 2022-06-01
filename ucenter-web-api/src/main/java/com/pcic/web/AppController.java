package com.pcic.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcic.core.common.dto.PageQuery;
import com.pcic.core.common.dto.ResponseMessage;
import com.pcic.dto.AppDto;
import com.pcic.service.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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
 * AppController
 *
 * @author Generator
 * @date 2021-11-30 15:50:45
 */
@Slf4j
@Tag(name = "应用配置表")
@RestController
@RequestMapping("/app")
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    /**
     * 列表
     *
     * @author Generator
     * @date 2021-11-30 15:50:45
     */
    @Operation(summary = "应用配置表-列表", description = "列表查询")
    @GetMapping("/list")
    public ResponseMessage<IPage<AppDto>> list(PageQuery pageQuery, AppDto appDto) {
        Page<AppDto> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        QueryWrapper<AppDto> appDtoQueryWrapper = Wrappers.query(appDto);
        return ResponseMessage.success(appService.page(page, appDtoQueryWrapper));
    }

    /**
     * 查询
     *
     * @author Generator
     * @date 2021-11-30 15:50:45
     */
    @Operation(summary = "应用配置表-查询", description = "ID查询")
    @GetMapping("/{id}")
    public ResponseMessage<AppDto> get(
            @Parameter(description = "应用配置表Id", required = true) @PathVariable("id") Long id) {
        return ResponseMessage.success(appService.getById(id));
    }

    /**
     * 新增
     *
     * @author Generator
     * @date 2021-11-30 15:50:45
     */
    @Operation(summary = "应用配置表-新增", description = "数据新增")
    @PostMapping("/add")
    public ResponseMessage<Boolean> add(@RequestBody AppDto appDto) {
        return ResponseMessage.success(appService.save(appDto));
    }

    /**
     * 更新
     *
     * @author Generator
     * @date 2021-11-30 15:50:45
     */
    @Operation(summary = "应用配置表-更新", description = "数据更新")
    @PostMapping("/update")
    public ResponseMessage<Boolean> update(@RequestBody AppDto appDto) {
        return ResponseMessage.success(appService.updateById(appDto));
    }

    /**
     * 删除
     *
     * @author Generator
     * @date 2021-11-30 15:50:45
     */
    @Operation(summary = "应用配置表-删除", description = "数据删除")
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage<Boolean> delete(
            @Parameter(description = "应用配置表Id列表", required = true) @RequestBody List<String> ids) {
        return ResponseMessage.success(appService.removeByIds(ids));
    }

}