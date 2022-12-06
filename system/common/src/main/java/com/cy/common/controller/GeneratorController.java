package com.cy.common.controller;

import com.cy.common.dao.DbInfoMapper;
import com.cy.common.generator.Info;
import com.cy.common.generator.MyBatisPlusGenerator;
import com.cy.common.utils.apiUtil.ApiResp;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Resource
    private DbInfoMapper dbInfoMapper1;

    @PostMapping("mpGenerator")
    public ApiResp mpGenerator() throws Exception {
        // 查询数据库的所有表名
        List<String> tablesNameList = dbInfoMapper1.getTablesNameList();

        if (CollectionUtils.isEmpty(tablesNameList)) {
            return ApiResp.error("库中还没有表结构");
        }

        String[] tableArray = tablesNameList.toArray(new String[tablesNameList.size()]);
        Info info = Info.builder()
                .driver("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/permission?useUnicode=true&characterEncoding-utr-8&useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("123456")
                .outputPath("D:\\project\\mine\\system\\project\\generator\\")
                .tableNames(tableArray)
                .build();
        MyBatisPlusGenerator.generaterCode(info);

        return ApiResp.success(tableArray);
    }
}
