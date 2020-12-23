//package com.secondhand.xiaoyi.utils;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
///**
// * @ClassName CodeGenerator
// * @Description TODO
// * @Author Gaosl
// * @Date 2020/12/5 20:46
// * @Version 1.0
// */
//public class CodeGenerator {
//    public static void main(String[] args) {
//        // 1、创建代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 2、全局配置
//        GlobalConfig gc = new GlobalConfig();
//        //绝对路径
//        gc.setOutputDir("F:\\ideaproject\\mybatis-plusout");
//        gc.setAuthor("Gaosl");
//        //生成后是否打开资源管理器
//        gc.setOpen(true);
//        //重新生成时文件是否覆盖
//        gc.setFileOverride(true);
//        //去掉Service接口的首字母I
//        gc.setServiceName("%sService");
//        //主键策略
//        gc.setIdType(IdType.ID_WORKER_STR);
//        //定义生成的实体类中日期类型
//        gc.setDateType(DateType.ONLY_DATE);
//        //开启Swagger2模式
//        gc.setSwagger2(true);
//        mpg.setGlobalConfig(gc);
//        // 3、数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/xiaoyi?serverTimezone=GMT%2B8&useUnicode=true&useSSL=false&characterEncoding=utf8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        dsc.setDbType(DbType.MYSQL);
//        mpg.setDataSource(dsc);
//        // 4、包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setParent("com.secondhand");
//        //模块名
//        pc.setModuleName("xiaoyi");
//        pc.setController("controller");
//        pc.setEntity("entity");
//        pc.setService("service");
//        pc.setMapper("mapper");
//        mpg.setPackageInfo(pc);
//        // 5、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        //"goods_want","action","admin","favorite","message","shoppingcart","sort","user"
//        strategy.setInclude("user");
//        //数据库表映射到实体的命名策略
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        //生成实体时去掉表前缀
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        //数据库表字段映射到实体的命名策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        // lombok 模型"" @Accessors(chain = true) setter链式操作
//        strategy.setEntityLombokModel(true);
//        //restful api风格控制器
//        strategy.setRestControllerStyle(true);
//        //url中驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(true);
//        mpg.setStrategy(strategy);
//        // 6、执行
//        mpg.execute();
//    }
//
//}
