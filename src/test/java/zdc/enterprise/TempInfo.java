package zdc.enterprise;

import java.util.HashMap;
import java.util.Map;

public class TempInfo {

    /**
     * 模版位置(相对路径)
     */
    private String templatePath;

    /**
     * 生成的文件路径(相对路径)
     */
    private String targetPath;

    /***
     * 生成的文件名前缀
     */
    private String targetFilePrefix;

    /***
     * 生成的文件名后缀(字节拼接)
     * eg :Controller
     */
    private String targetFileSuffix;

    /***
     * 生成的文件名类型后缀
     * eg : .java  .xml
     */
    private String targetFileTypeSuffix;

    /***
     * 需要在模版中使用的变量,可以替换程序默认变量 和全局变量
     */
    private Map<String,Object> customParams =new HashMap<>();




    public TempInfo(String templatePath, String targetPath,String targetFilePrefix,String targetFileSuffix,String targetFileTypeSuffix) {
        this.templatePath = templatePath;
        this.targetPath = targetPath;
        this.targetFilePrefix = targetFilePrefix;
        this.targetFileSuffix = targetFileSuffix;
        this.targetFileTypeSuffix = targetFileTypeSuffix;
    }



    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public Map<String, Object> getCustomParams() {
        this.customParams.remove("sysParams");
        return this.customParams;
    }

    public void setCustomParams(Map<String, Object> customParams) {
        this.customParams = customParams;
    }

    public Map<String, Object> putParam(String key,Object value){
        this.customParams.put(key,value);
        return this.customParams;
    }

    public TempInfo addParam(String key,Object value){
        this.customParams.put(key,value);
        return this;
    }

    public String getTargetFileSuffix() {
        return targetFileSuffix;
    }

    public void setTargetFileSuffix(String targetFileSuffix) {
        this.targetFileSuffix = targetFileSuffix;
    }

    public String getTargetFilePrefix() {
        return targetFilePrefix;
    }

    public void setTargetFilePrefix(String targetFilePrefix) {
        this.targetFilePrefix = targetFilePrefix;
    }

    public String getTargetFileTypeSuffix() {
        return targetFileTypeSuffix;
    }

    public void setTargetFileTypeSuffix(String targetFileTypeSuffix) {
        this.targetFileTypeSuffix = targetFileTypeSuffix;
    }
}
