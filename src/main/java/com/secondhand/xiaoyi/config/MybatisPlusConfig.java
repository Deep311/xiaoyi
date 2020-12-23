package com.secondhand.xiaoyi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Config
 * @Description TODO
 * @Author Gaosl
 * @Date 2020/12/3 21:14
 * @Version 1.0
 */
@Configuration
@MapperScan("com.secondhand.xiaoyi.mapper")
public class MybatisPlusConfig {
    /**
     * @Author gaosl
     * @Description  //分页插件
     * @Date 21:15 2020/12/3
     * @Param []
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
