package zdc.enterprise;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.util.HashMap;
import java.util.Map;

public class TempConfig {

    /**
     * freemarker模版编码方式
     */
    public  String encoding ="UTF-8";

    /**
     * 文件目录是否自动创建
     */
    public boolean targetDirAutoCreate =true;

    /**
     * 文件已存在是否删除
     * true 删除旧的文件再生成新的
     * false 将新文件加一个时间后缀
     */
    public boolean targetExistsDel =false;


    /***
     * 数据库配置
     */
    public  String driverClass = "com.mysql.cj.jdbc.Driver";
    public  String dbUrl = "jdbc:mysql://localhost:3306/database?serverTimezone=UTC";
    public  String dbUser = "root";
    public  String dbPassword = "root";

    public String[] tableNames ;

    /**
     * freemarker的配置
     */
    private Configuration configuration ;

    /***
     * 需要在模版中使用的全局变量,可以替换程序默认变量,
     */
    private Map<String,Object> customParams =new HashMap<>();


    /**
     * 系统将freemarker模版缓存起来,在以后多次调用的时候提供方便
     */
    private Map<String, Template> templateMap= new HashMap<>();



    public TempConfig() {
        this.configuration= new Configuration();
        this.configuration.setObjectWrapper(new DefaultObjectWrapper());
    }

    public void setDatabaseInfo( String driverClass ,  String dbUrl, String dbUser, String dbPassword){
        this.driverClass=driverClass;
        this.dbUrl=dbUrl;
        this.dbUser=dbUser;
        this.dbPassword=dbPassword;
    }



    public Template getTemplate(String key) {
        return this.templateMap.get(key);
    }
    public void setTemplate(String key,Template template) {
       this.templateMap.put(key,template);
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Map<String, Object> getCustomParams() {
        this.customParams.remove("sysParams");
        return  this.customParams;
    }

    public void setCustomParams(Map<String, Object> customParams) {
        this.customParams = customParams;
    }
}
