package com.pcic.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.pcic.enums.InvalidFlagEnum;
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

    public static final String USER_ADMIN = "SYSTEM";

    /**
     * 新增，默认会判断值为空才赋值
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "invalidFlag", Integer.class, InvalidFlagEnum.VAILD.getCode());
        this.strictInsertFill(metaObject, "createdBy", String.class, USER_ADMIN);
        this.strictInsertFill(metaObject, "updatedBy", String.class, USER_ADMIN);
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
        this.strictUpdateFill(metaObject, "updatedBy", String.class, USER_ADMIN);
        this.strictUpdateFill(metaObject, "updatedTime", Date.class, new Date());
    }
}
