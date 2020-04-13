package zdc.enterprise;

import java.util.ArrayList;

public class AutoCode {

    public static void main(String[] args) throws Exception {


        //建议能在模版上写死的就不要配置变量
        //没有模版之间的关联关系.如果需要的话参照simple/relation-controller.ftl 在模版中实现


        TempConfig tempConfig = new TempConfig();
        tempConfig.targetExistsDel=true;

        //数据库配置
        tempConfig.setDatabaseInfo("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/bigdata_test?serverTimezone=UTC",
                "devuser",
                "devuser");


        //需要生成文件的表
        tempConfig.tableNames = new String[]{"d_field_check"};

        //全局自定义参量
        //tempConfig.setCustomParams();


        //需要生成的模版路径和对应的路径
        ArrayList<TempInfo> templateList = new ArrayList<>();

        String parentPath ="src/main/resources/templates/simple/";

     /*   templateList.add(
                //模版路径及其对应的文件存放路径
                new TempInfo("src/main/resources/templates/lombok/controller.ftl",
                        "src/main/java/zdc/enterprise/controller/",
                        "",
                        "Controller",
                        ".java")
                        //模版的自定义参量
                        .addParam("interfaceName","zdc.enterprise.controller")
        );*/

      /*  templateList.add(
                //模版路径及其对应的文件存放路径
                new TempInfo("src/main/resources/templates/simple/relation-controller.ftl",
                        "src/main/java/zdc/enterprise/controller/",
                        "",
                        "Controller",
                        ".java")
                        //模版的自定义参量
                        .addParam("interfaceName","zdc.enterprise.controller")
        );*/

      /*  templateList.add(
                //模版路径及其对应的文件存放路径
                new TempInfo("src/main/resources/templates/simple/entity.ftl",
                        "src/main/java/zdc/enterprise/entity/",
                        "",
                        "",
                        ".java")
                        //模版的自定义参量
                        .addParam("interfaceName","zdc.enterprise.entity")
        );*/

        templateList.add(
                //模版路径及其对应的文件存放路径
                new TempInfo("src/main/resources/templates/simple/mapper.ftl",
                        "src/main/resources/mappers/",
                        "",
                        "Mapper",
                        ".xml")
                        //模版的自定义参量
                        .addParam("entityPath","zdc.enterprise.entity")
                        .addParam("mapperPath","zdc.enterprise.mapper")
        );

        //开始生成
        new TempFactory(tempConfig,templateList).start();

    }




}
