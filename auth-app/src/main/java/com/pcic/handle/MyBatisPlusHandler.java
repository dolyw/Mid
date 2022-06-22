package com.pcic.handle;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.pcic.enums.InvalidFlagEnum;
import com.sinosoft.cloud.common.security.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatisPlusHandler
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/29 19:43
 */
@Component
public class MyBatisPlusHandler implements MetaObjectHandler {

    public static final String SYSTEM = "SYSTEM";

    /**
     * 新增，默认会判断值为空才赋值
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "invalidFlag", Integer.class, InvalidFlagEnum.VAILD.getCode());
        this.strictInsertFill(metaObject, "createdBy", String.class, getUser());
        this.strictInsertFill(metaObject, "updatedBy", String.class, getUser());
        this.strictInsertFill(metaObject, "createdTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updatedTime", Date.class, new Date());
    }

    /**
     * 更新，默认会判断值为空才赋值
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedBy", String.class, getUser());
        this.strictUpdateFill(metaObject, "updatedTime", Date.class, new Date());
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public String getUser() {
        try {
            String username = SecurityUtils.getUser().getUsername();
            if (StrUtil.isNotBlank(username)) {
                return username;
            }
            return SYSTEM;
        } catch (Exception e) {
            return "E-" + SYSTEM;
        }
    }
}
