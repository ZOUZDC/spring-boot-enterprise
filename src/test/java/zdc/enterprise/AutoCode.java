package zdc.enterprise;

import java.io.File;
import java.util.ArrayList;

public class AutoCode {


    //mapperxml生成路径
    static String  mapperXmlParentPath ="";
    //模版文件路径
    static String  templateParentPath ="";

    static TempConfig tempConfig=new TempConfig();

    public static void main(String[] args) throws Exception {


        //建议能在模版上写死的就不要配置变量
        //没有模版之间的关联关系.如果需要的话参照simple/relation-controller.ftl 在模版中实现

        //如果存在对应的类是否删除
        tempConfig.targetExistsDel=true;

        //数据库配置  TODO 需要根据自己的项目修改
        tempConfig.setDatabaseInfo("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql:///zdc_test?serverTimezone=UTC",
                "root",
                "123123");


        //需要生成文件的表  TODO 需要设置
        tempConfig.tableNames = new String[]{"student"};

        //全局自定义参量   TODO 需要根据自己的项目修改
        tempConfig

                .setCustomParams("basePackage","zdc.enterprise")//父包名 ,

                .setCustomParams("entityName","entity") //entity包名
                .setCustomParams("dtoName","dto") //dto包名
                //.setCustomParams("voName","vo") //vo包名

                .setCustomParams("controllerName","controller") //controller包名
                .setCustomParams("serviceName","service") //service包名
                .setCustomParams("serviceImplName","service.impl") //serviceImpl包名
                //.setCustomParams("serviceImplName",tempConfig.getCustomParams("serviceImplName")+".impl") //serviceImpl包名 和serviceName有关联
                .setCustomParams("mapperName","mapper") //mapper包名

        ;
        //mapperxml 的路径 TODO 需要根据自己的项目修改,正常情况虚无修改
        mapperXmlParentPath ="src/main/resources/mappers/";


        //此处需要注意, 如果是idea下多modules的项目,运行时需要在working directory 参数中添加该module名  TODO 需要根据自己的项目修改
        templateParentPath ="src/main/resources/templates/simple/";



        //需要生成的模版信息
        ArrayList<TempInfo> templateList = new ArrayList<>();

        templateList.add(entity());
        templateList.add(dto());
        templateList.add(controller());
        templateList.add(service());
        templateList.add(serviceImpl());
        templateList.add(mapper());
        templateList.add(mapperXml());


        //开始生成
        new TempFactory(tempConfig,templateList).start();

    }




    ///----------------------------------------------以下首次使用暂时无需关注-------------------------------------------

















    private static TempInfo entity(){
        return  new TempInfo(templateParentPath+"entity.ftl",
                tempConfig.getCustomParams("basePackage")+ File.separator+tempConfig.getCustomParams("entityName"),
                ".java")
                 //模版的自定义参量
                .addParam("cParams","可以自定义参量");
    }


    private static TempInfo dto(){
        return  new TempInfo(templateParentPath+"dto.ftl",
                tempConfig.getCustomParams("basePackage")+ File.separator+tempConfig.getCustomParams("dtoName"),
                "","Dto",
                ".java");

    }

    private  static TempInfo controller(){
     return  new TempInfo(templateParentPath+"controller.ftl",
             tempConfig.getCustomParams("basePackage")+ File.separator+tempConfig.getCustomParams("controllerName"),
                "","Controller",
                ".java");

    }

    private  static TempInfo service(){
        return  new TempInfo(templateParentPath+"service.ftl",
                tempConfig.getCustomParams("basePackage")+ File.separator+tempConfig.getCustomParams("serviceName"),
                "","Service",
                ".java");

    }

    private  static TempInfo serviceImpl(){
        String name = tempConfig.getCustomParams("serviceImplName");
        String path =File.separator+"name";
        if(name.indexOf(".")!=-1){
            path ="";
            for (String p : name.split(".")) {
                path+=File.separator+"p";
            }
        }

        return  new TempInfo(templateParentPath+"serviceImpl.ftl",
                tempConfig.getCustomParams("basePackage")+ path,
                "","ServiceImpl",
                ".java");

    }

    private  static TempInfo mapper(){
        return  new TempInfo(templateParentPath+"mapperInterface.ftl",
                tempConfig.getCustomParams("basePackage")+ File.separator+tempConfig.getCustomParams("mapperName"),
                "","Mapper",
                ".java");

    }

    private  static TempInfo mapperXml(){
        return  new TempInfo(templateParentPath+"mapper.ftl",
                mapperXmlParentPath,
                "","Mapper",
                ".xml");

    }





}
