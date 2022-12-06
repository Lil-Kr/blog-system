package com.cy.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @creator Screenly
 * @create_time 2020/4/27
 * @description
 **/
public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        String tableNames = "sys_acl";
        String[] tableArray = tableNames.split(",");
        Info info = Info.builder()
                .driver("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/permission?useUnicode=true&characterEncoding-utr-8&useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("123456")
                .outputPath("D:\\project\\mine\\system\\project\\generator\\")
                .tableNames(tableArray)
                .build();
        generaterCode(info);
    }

    public static void generaterCode (Info info) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
                .setAuthor("CY")
                // 生成路径
                .setOutputDir(info.getOutputPath())
                // 文件覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)
                // 设置日期格式
                .setDateType(DateType.ONLY_DATE)
                .setControllerName("%sController")
                // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
//                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                //生成基本的resultMap
                .setBaseResultMap(true)
                //生成基本的SQL片段
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName(info.getDriver())
                .setUrl(info.getUrl())
                .setUsername(info.getUsername())
                .setPassword(info.getPassword());

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        //全局大写命名
        stConfig.setCapitalMode(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true) //使用lombk
                .setRestControllerStyle(true) // rest风格
        ;

//                .setInclude(info.getTableNames()); // 生成的表, 支持多表一起生成，以数组形式填写


        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.cy.sys")
                .setMapper("dao")
                .setService("service")
                .setController("controller")
                .setEntity("pojo.entity")
                .setXml("mapper");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
        System.out.println("======= 代码生成完毕 ========");
    }

}
