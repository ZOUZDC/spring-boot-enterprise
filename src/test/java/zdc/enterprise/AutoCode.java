package zdc.enterprise;

import java.util.ArrayList;

public class AutoCode {

    public static void main(String[] args) throws Exception {


        //建议能在模版上写死的就不要配置变量


        TempConfig tempConfig = new TempConfig();
        tempConfig.targetExistsDel=true;

        //数据库配置
        tempConfig.setDatabaseInfo("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql:///zdc_test?serverTimezone=UTC",
                "root",
                "123123");


        //需要生成文件的表
        tempConfig.tableNames = new String[]{"student2"};

        //全局自定义参量
        //tempConfig.setCustomParams();


        //需要生成的模版路径和对应的路径
        ArrayList<TempInfo> templateList = new ArrayList<>();

        String templatesPath ="src/main/resources/templates/simple/";

        String mapperInterfacePath ="src/main/java/zdc/enterprise/mapper/";


        //entity
        templateList.add( new TempInfo(templatesPath+"entity.ftl", "src/main/java/zdc/enterprise/entity/", ".java"));

        //Dto
        templateList.add( new TempInfo(templatesPath+"dto.ftl", "src/main/java/zdc/enterprise/dto/", "","Dto",".java"));

        //controller
        templateList.add(
                new TempInfo(templatesPath+"controller.ftl", "src/main/java/zdc/enterprise/controller/",
                        "", "Controller", ".java")
        );

        //service
        templateList.add(
                new TempInfo(templatesPath+"service.ftl", "src/main/java/zdc/enterprise/service/",
                        "", "Service", ".java")
        );

        //serviceImpl
        templateList.add(
                new TempInfo(templatesPath+"serviceImpl.ftl", "src/main/java/zdc/enterprise/service/impl/",
                        "", "ServiceImpl", ".java")
        );

        //mapperInterface
        templateList.add(
                new TempInfo(templatesPath+"mapperInterface.ftl", "src/main/java/zdc/enterprise/mapper/",
                        "", "Mapper", ".java")
        );


        templateList.add(
                //模版路径及其对应的文件存放路径
                new TempInfo(templatesPath+"mapper.ftl","src/main/resources/mappers/",
                        "","Mapper",".xml")
                        //模版的自定义参量
                        .addParam("entityPath","zdc.enterprise.entity")
                        .addParam("mapperPath","zdc.enterprise.mapper")
        );



        //开始生成
        new TempFactory(tempConfig,templateList).start();

    }

/* 完整的一个模版的实现
 templateList.add(
        //模版路径及其对应的文件存放路径
        new TempInfo(templatesPath+"controller.ftl",
                "src/main/java/zdc/enterprise/controller/",
                "",
                "Controller",
                ".java")
                //模版的自定义参量
                .addParam("interfaceName","zdc.enterprise.controller")
        );
*/


}
