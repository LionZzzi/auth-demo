package com.eric.authdemo.config.mybatisplus;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.eric.authdemo.constant.MybatisPlusConstants;
import com.eric.authdemo.model.SecurityUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充策略
 * 如果token为空,则代表公用接口(无创建人,更新人)
 *
 * @author Eric
 * @date 2019-05-23
 */
@Slf4j
@Component
public class AutoFillConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("===== 执行SQL插入填充操作 =====");
        if (ObjectUtil.isNull(userDetails)) {
            this.strictInsertFill(metaObject, MybatisPlusConstants.CREATE_USER_ID, Long.class, null);
            this.strictInsertFill(metaObject, MybatisPlusConstants.UPDATE_USER_ID, Long.class, null);
        } else {
            this.strictInsertFill(metaObject, MybatisPlusConstants.CREATE_USER_ID, Long.class, userDetails.getId());
            this.strictInsertFill(metaObject, MybatisPlusConstants.UPDATE_USER_ID, Long.class, userDetails.getId());
        }
        this.strictInsertFill(metaObject, MybatisPlusConstants.CREATE_TIME, Date.class, now);
        this.strictInsertFill(metaObject, MybatisPlusConstants.UPDATE_TIME, Date.class, now);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SecurityUserDetails userDetails = (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("===== 执行SQL更新填充操作 =====");
        if (ObjectUtil.isNull(userDetails)) {
            this.strictInsertFill(metaObject, MybatisPlusConstants.UPDATE_USER_ID, Long.class, null);
        } else {
            this.strictInsertFill(metaObject, MybatisPlusConstants.UPDATE_USER_ID, Long.class, userDetails.getId());
            this.strictInsertFill(metaObject, MybatisPlusConstants.UPDATE_TIME, Date.class, new Date());
        }
    }
}

