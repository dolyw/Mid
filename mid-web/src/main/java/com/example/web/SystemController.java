package com.example.web;

import com.example.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * AuthController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 14:46
 */
@RestController
@RequestMapping("/system")
public class SystemController {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private BusinessService businessService;

    /**
     * 菜单
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/27 17:32
     */
    @GetMapping("/menu/{username}")
    public String menu(@PathVariable("username") String username) throws Exception {
        return "{\"data\":{\"routes\":[{\"path\":\"/system\",\"name\":\"系统管理\",\"component\":\"Layout\",\"meta\":{\"title\":\"系统管理\",\"icon\":\"el-icon-set-up\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":true,\"children\":[{\"path\":\"/system/user\",\"name\":\"用户管理\",\"component\":\"page/system/user/Index\",\"meta\":{\"title\":\"用户管理\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/system/role\",\"name\":\"角色管理\",\"component\":\"page/system/role/Index\",\"meta\":{\"title\":\"角色管理\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/system/menu\",\"name\":\"菜单管理\",\"component\":\"page/system/menu/Index\",\"meta\":{\"title\":\"菜单管理\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/system/dept\",\"name\":\"部门管理\",\"component\":\"page/system/dept/Index\",\"meta\":{\"title\":\"部门管理\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/client\",\"name\":\"客户端管理\",\"component\":\"page/system/client/Index\",\"meta\":{\"title\":\"客户端管理\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false}]},{\"path\":\"/monitor\",\"name\":\"系统监控\",\"component\":\"Layout\",\"meta\":{\"title\":\"系统监控\",\"icon\":\"el-icon-data-line\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":true,\"children\":[{\"path\":\"/monitor/systemlog\",\"name\":\"系统日志\",\"component\":\"page/monitor/systemlog/Index\",\"meta\":{\"title\":\"系统日志\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/monitor/loginlog\",\"name\":\"登录日志\",\"component\":\"page/monitor/loginlog/Index\",\"meta\":{\"title\":\"登录日志\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false}]},{\"path\":\"/gen\",\"name\":\"代码生成\",\"component\":\"Layout\",\"meta\":{\"title\":\"代码生成\",\"icon\":\"el-icon-printer\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":true,\"children\":[{\"path\":\"/gen/config\",\"name\":\"基础配置\",\"component\":\"page/gen/config/Index\",\"meta\":{\"title\":\"基础配置\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/gen/generate\",\"name\":\"生成代码\",\"component\":\"page/gen/generate/Index\",\"meta\":{\"title\":\"生成代码\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false}]},{\"path\":\"/others\",\"name\":\"其他模块\",\"component\":\"Layout\",\"meta\":{\"title\":\"其他模块\",\"icon\":\"el-icon-shopping-bag-1\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":true,\"children\":[{\"path\":\"/others/eximport\",\"name\":\"导入导出\",\"component\":\"page/others/eximport/Index\",\"meta\":{\"title\":\"导入导出\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/others/blog\",\"name\":\"个人博客\",\"component\":\"page/others/blog/Index\",\"meta\":{\"title\":\"个人博客\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false},{\"path\":\"/others/datapermission\",\"name\":\"数据权限\",\"component\":\"page/others/datapermission/Index\",\"meta\":{\"title\":\"数据权限\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false}]},{\"path\":\"/components\",\"name\":\"静态组件\",\"component\":\"Layout\",\"meta\":{\"title\":\"静态组件\",\"icon\":\"el-icon-present\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":true,\"children\":[{\"path\":\"/two\",\"name\":\"二级菜单\",\"component\":\"demos/two/Index\",\"meta\":{\"title\":\"二级菜单\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":true,\"children\":[{\"path\":\"/three\",\"name\":\"三级菜单\",\"component\":\"demos/two/three/Index\",\"meta\":{\"title\":\"三级菜单\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false}]},{\"path\":\"/components/tinymce\",\"name\":\"富文本编辑器\",\"component\":\"demos/tinymce\",\"meta\":{\"title\":\"富文本编辑器\",\"icon\":\"\",\"breadcrumb\":true},\"hidden\":false,\"alwaysShow\":false}]},{\"path\":\"*\",\"name\":\"404\",\"component\":\"error-page/404\",\"hidden\":false,\"alwaysShow\":false}],\"permissions\":[\"user:view\",\"role:view\",\"menu:view\",\"dept:view\",\"log:view\",\"role:add\",\"menu:add\",\"dept:add\",\"user:export\",\"role:export\",\"menu:export\",\"dept:export\",\"log:export\",\"monitor:loginlog\",\"loginlog:export\",\"others:eximport\",\"gen:config\",\"gen:generate\",\"gen:generate:gen\",\"client:view\",\"client:add\",\"client:decrypt\",\"monitor:dashboard\",\"others:datapermission\",\"job:view\",\"job:log:view\",\"job:add\",\"job:export\",\"job:log:export\"]}}";
    }

    /**
     * msg
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:48
     */
    @GetMapping("/msg")
    public String msg() {
        return businessService.handle();
    }

}
