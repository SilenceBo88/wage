package com.wage;

import com.google.common.base.CaseFormat;
import com.wage.core.constant.ProjectConstant;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: zb
 * @Date: Created in 2018/5/29 14:56
 * @Description: 代码生成器，根据数据表名称生成对应的Model、Mapper简化开发。
 */
public class CodeGenerator {

    // JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wage?useUnicode=true&characterEncoding=utf-8";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "zhaobo..";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String JAVA_PATH = "src/main/java"; // java文件路径
    private static final String RESOURCES_PATH = "src/main/resources";// 资源文件路径

    // 模板位置
    private static final String TEMPLATE_FILE_PATH = "src/test/resources/template/generator";
    // 生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(ProjectConstant.SERVICE_PACKAGE);
    // 生成的Service实现存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(ProjectConstant.SERVICE_IMPL_PACKAGE);
    // 生成的Controller存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(ProjectConstant.CONTROLLER_PACKAGE);

    // @author
    private static final String AUTHOR = "zb";
    // @date
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());


    /**
     * genCode("输入表名","输入自定义Model名称");
     * 如果想创建所有表，请输入"%"
     * @param args
     */
    public static void main(String[] args) {
        genCode("admin");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。 如输入表名称 "t_user_detail" 将生成
     * TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames
     *            数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCode(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User"
     * 将生成 User、UserMapper、UserService ...
     *
     * @param tableName
     *            数据表名称
     * @param modelName
     *            自定义的 Model 名称
     */
    public static void genCode(String tableName, String modelName) {
        genModelAndMapper(tableName, modelName);
        genService(tableName);
        genController(tableName);
    }

    public static void genModelAndMapper(String tableName, String modelName) {
        Context context = getContext();

        JDBCConnectionConfiguration jdbcConnectionConfiguration = getJDBCConnectionConfiguration();
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = getPluginConfiguration();
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = getJavaModelGeneratorConfiguration();
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = getSqlMapGeneratorConfiguration();
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration =getJavaClientGeneratorConfiguration();
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        tableConfiguration.setDomainObjectName(modelName);
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();
            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)){
            modelName = tableNameConvertUpperCamel(tableName);
        }
        System.out.println(modelName + ".java 生成成功");
        System.out.println(modelName + "Mapper.java 生成成功");
        System.out.println(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            //模板所需要的参数
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", ProjectConstant.BASE_PACKAGE);
            data.put("basePackageService", ProjectConstant.SERVICE_PACKAGE);
            data.put("basePackageServiceImpl", ProjectConstant.SERVICE_IMPL_PACKAGE);
            data.put("basePackageModel", ProjectConstant.MODEL_PACKAGE);
            data.put("basePackageDao", ProjectConstant.MAPPER_PACKAGE);

            File file = new File(JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data, new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data, new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();
            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
            data.put("baseRequestMapping", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelNameUpperCamel));
            data.put("basePackage", ProjectConstant.BASE_PACKAGE);
            data.put("basePackageController", ProjectConstant.CONTROLLER_PACKAGE);
            data.put("basePackageService", ProjectConstant.SERVICE_PACKAGE);
            data.put("basePackageModel", ProjectConstant.MODEL_PACKAGE);

            File file = new File(JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static Context getContext(){
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
        return context;
    }

    private static JDBCConnectionConfiguration getJDBCConnectionConfiguration(){
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        return jdbcConnectionConfiguration;
    }

    private static PluginConfiguration getPluginConfiguration(){
        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", ProjectConstant.MAPPER_INTERFACE_REFERENCE);
        return pluginConfiguration;
    }

    private static JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration(){
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(ProjectConstant.MODEL_PACKAGE);
        javaModelGeneratorConfiguration.addProperty("enableSubPackages","true");
        javaModelGeneratorConfiguration.addProperty("trimStrings","true");
        return javaModelGeneratorConfiguration;
    }

    private static SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration(){
        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
        return sqlMapGeneratorConfiguration;
    }

    private static JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration(){
        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(ProjectConstant.MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        return javaClientGeneratorConfiguration;
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }
}
