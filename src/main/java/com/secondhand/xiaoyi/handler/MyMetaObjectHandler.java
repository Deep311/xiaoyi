package com.secondhand.xiaoyi.handler;




import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @ClassName MyMetaObjectHandler
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/11/30 14:03
 * @Version 1.0
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        log.info("填充gmtCreate字段");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("填充gmtModified字段");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

//    @Override
//    public void insertFill(MetaObject metaObject) {
//        log.info("start insert fill ....");
//        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
//
//    }
//    //更新时的填充策略
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        log.info("start update fill ....");
//        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
//    }
}

