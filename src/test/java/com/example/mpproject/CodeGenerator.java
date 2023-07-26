package com.example.mpproject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CodeGenerator {
    private static final String URL = "jdbc:mysql://localhost:3306/project?serverTimezone=GMT%2b8";
    private static final String OUTPUT_DIR = "D:/Spring Code Compile Project/0725_mp_mook/mp-project/src/main/java";
    private static final String MAPPER_XML_DIR = "D:/Spring Code Compile Project/0725_mp_mook/mp-project/src/main/java/com/example/mpproject/mapper";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL,"root","root")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                                //覆盖已生成文件默认为false
                                .fileOverride()
                                //指定输出目录
                                .outputDir(OUTPUT_DIR)
                        //开启 swagger 模式 默认为false

                )
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？"))
                        //路径配置信息 Collections.singletonMap(OutputFile.mapperXml, "D://")
                        .pathInfo(Collections.singletonMap(OutputFile.mapper,MAPPER_XML_DIR))
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        //增加过滤表前缀
                        .addTablePrefix("tbl_")
                        //实体策略配置
                        .entityBuilder()
                        //开启链式模型    默认值:false
                        .enableChainModel()
                        //开启 lombok 模型    默认值:false
                        .enableLombok()
                        //开启生成实体时生成字段注解    默认值:false
                        .enableTableFieldAnnotation()
                        //添加表字段填充
                        .addTableFills()
                        .addTableFills(
                                new Column(
                                        "create_time", FieldFill.INSERT
                                ),
                                new Column(
                                        "modified_time", FieldFill.UPDATE
                                ),
                                new Column(
                                        "create_account_id", FieldFill.INSERT
                                ),
                                new Column(
                                        "modified_account_id",FieldFill.INSERT
                                )
                        )
                        .logicDeleteColumnName("deleted")
                        //controller 策略配置
                        .controllerBuilder()
                        //开启驼峰转连字符    默认值:false
                        .enableHyphenStyle()
                        //开启生成@RestController 控制器    默认值:false
                        .enableRestStyle()
                        //格式化文件名称
                        .formatFileName("%sController")


                        //mapper 策略配置
                        .mapperBuilder()
                        //启用 BaseResultMap 生成    默认值:false
                        .enableBaseResultMap()
                        //格式化 mapper 文件名称
                        .formatMapperFileName("%sMapper")
                        //格式化 xml 实现类文件名称
                        .formatXmlFileName("%sMapper")

                        //service 策略配置
                        .serviceBuilder()
                        //格式化 service 接口文件名称
                        .formatServiceFileName("%sService")
                        //格式化 service 实现类文件名称
                        .formatServiceImplFileName("%sServiceImpl")
                        .build()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();


    }

    /**
     * 处理所有表的情况
     * @param tables
     * @return
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}


