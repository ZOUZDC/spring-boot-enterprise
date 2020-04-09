package zdc.enterprise;

import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempFactory {

    private TempConfig tempConfig;

    private List<TempInfo> tempInfoList;


    public TempFactory(TempConfig tempConfig, List<TempInfo> tempInfoList) {
        this.tempConfig = tempConfig;
        this.tempInfoList = tempInfoList;
    }

    public void start(){

        if(this.tempInfoList==null||this.tempInfoList.size()==0){
            System.err.println("--------------------没有模版无法执行任务--------------------");
            return;
        }


        //检查需要生成的表的数据
        if (this.tempConfig.tableNames==null ||this.tempConfig.tableNames.length==0){
            System.err.println("--------------------没有需要执行表信息--------------------");
            return;
        }
        for (String tableName : this.tempConfig.tableNames) {
            if(tableName==null||tableName.trim()==""){
                System.err.println("--------------------提供的表信息错误--------------------");
                return;
            }
        }


        //检查文件生成路径
        if(!this.checkDir()){
            return ;
        }

        //加载模版
        try {
            this.setTemplateMap();
        } catch (IOException e) {
            System.err.println("--------------------freemarker模版加载失败,具体请看一下报错--------------------");
            e.printStackTrace();
            return ;
        }


        //连接数据库
        System.out.println("--------------------连接数据库--------------------");
        try{
            MysqlUtil.init(this.tempConfig.driverClass, this.tempConfig.dbUrl, this.tempConfig.dbUser, this.tempConfig.dbPassword);
        }catch (Exception e){
            System.err.println("--------------------数据库连接信息存在问题,具体请看一下报错--------------------");
            e.printStackTrace();
            return ;
        }


        //全局自定义参量覆盖系统参量
        Map<String,Object> params = new HashMap<>(TempUtil.sysParams);
        params.putAll(this.tempConfig.getCustomParams());


        //获取对应的表信息并生成文件
        System.out.println("--------------------开始执行任务--------------------");
        List<Map<String, String>> fields=null;
        String tableName="";

        for (String node : this.tempConfig.tableNames) {
            tableName = node.trim();
            //获取字段信息
            try{
                fields = TempUtil.getFieldsFromTable(MysqlUtil.getTableCloumns(tableName));
            }catch (Exception e){
                System.err.println("--------------------处理数据出错,请查看报错--------------------");
                e.printStackTrace();
                return ;
            }


            Map<String,Object> tempMap = new HashMap<>(params);

            tempMap.put("sysTableName",tableName);
            tempMap.put("sysParams",fields); //此字段不可被覆盖

            buildFile(TempUtil.column2Property(tableName),tempMap);
        }


    }

    private  Map<String,Object> getParams(){
        Map<String,Object> params = new HashMap<>();
        //系统参量
        params.putAll(TempUtil.sysParams);
        //全局自定义参量
        params.putAll(this.tempConfig.getCustomParams());
        return params;
    }


    /**
     * 检查文件生成路径
     * @return
     */
    private boolean checkDir()  {
        for (TempInfo tempInfo : this.tempInfoList) {
            String targetPath = tempInfo.getTargetPath();
            File dir = new File(targetPath);
            if (!dir.exists()) {
                if(!this.tempConfig.targetDirAutoCreate){
                    System.out.println("--------------------文件生成路径存在问题--------------------");
                    System.out.println("设置不自动创建路径,且文件生成路径不存在:"+targetPath);
                   return false;
                }
                dir.mkdirs();
            }
        }
        return true;
    }

    /**
     * 加载模版
     * @throws IOException
     */
    private void setTemplateMap() throws IOException {
        for (TempInfo tempInfo : this.tempInfoList) {
            this.tempConfig.setTemplate(tempInfo.getTemplatePath(),this.tempConfig.getConfiguration().getTemplate(tempInfo.getTemplatePath(), this.tempConfig.encoding));
        }
    }


    /**
     * 生成数据
     * @param params
     * @return
     */
    private boolean buildFile(String sysFieldTableNamex, Map<String,Object> params){
        //将数据融合

        String TableNameUp = TempUtil.toUpString(sysFieldTableNamex);
        params.put("sysFieldTableName",TableNameUp);

        for (TempInfo tempInfo : this.tempInfoList) {
            Map<String, Object> tempMap = new HashMap<>(params);
            tempMap.putAll(tempInfo.getCustomParams());

            String tableName = tempInfo.getTargetFilePrefix() + TableNameUp + tempInfo.getTargetFileSuffix();
            String sysFieldTableNameUp = TempUtil.toUpString(tableName);
            tempMap.put("sysFieldTableNameSuffix", sysFieldTableNameUp);


            String sysFieldTableName = sysFieldTableNameUp+tempInfo.getTargetFileTypeSuffix();

            try{
                File file =new File(tempInfo.getTargetPath()+sysFieldTableName);
                if(file.exists()){
                    if(this.tempConfig.targetExistsDel){
                        file.delete();
                    }else{
                        sysFieldTableName+="_"+System.currentTimeMillis();
                        file=new File(tempInfo.getTargetPath()+sysFieldTableName);
                    }
                }
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),  this.tempConfig.encoding));
                this.tempConfig.getTemplate(tempInfo.getTemplatePath()).process(tempMap,out);
                out.flush();
                out.close();
                System.out.println("--------------------"+sysFieldTableName+"已生成--------------------");
            }catch (IOException | TemplateException e){
                System.err.println("--------------------模版生成文件存在问题--------------------");
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }



}
