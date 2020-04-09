package zdc.enterprise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempUtil {


    public static Map sysParams;
    static {
        //系统需要的参量
        sysParams = new HashMap<String, String>();
        //有前后缀的
        sysParams.put("sysFieldTableNameSuffix","实体类表名加前后缀");
        //无前后缀
        sysParams.put("sysFieldTableName","实体类表名不加前后缀");
        sysParams.put("sysTableName","数据库表名");
        sysParams.put("sysParams","列信息"); //此字段不可被覆盖
    }

    // 数据库类型转属性类型
    public static Map<String, String> dataTypeMap;

    static {
        //建议非基本类型将包名也添加上
        dataTypeMap = new HashMap<String, String>();
        dataTypeMap.put("varchar", "String");
        dataTypeMap.put("char", "String");
        dataTypeMap.put("int", "Integer");
        dataTypeMap.put("bigint", "Long");
        dataTypeMap.put("tinyint", "Boolean");
        dataTypeMap.put("float", "Float");
        dataTypeMap.put("double", "Double");

        dataTypeMap.put("decimal", "java.math.BigDecimal");
        dataTypeMap.put("date", "java.util.Date");
        dataTypeMap.put("time", "java.util.Date");
        dataTypeMap.put("datetime", "java.util.Date");
        dataTypeMap.put("timestamp", "java.util.Date");

    }


    /**
     * 将数据库字段解析为模板参数
     *
     * @return
     */
    public static List<Map<String, String>> getFieldsFromTable(List<Map<String, String>> columnList) {

        for (Map<String, String> column : columnList) {
            column.put("fieldName", column2Property(column.get("columnName")));// 实体属性名
            column.put("fieldType", columnType2FieldType(column.get("dataType")));// 实体属性类型
            column.put("fieldNote", column.get("comment"));// 字段说明，即属性说明
        }
        return columnList;
    }


    /**
     * 下划线转驼峰
     *
     * @param fieldName
     * @return
     */
    public static String column2Property(String fieldName) {
        StringBuffer result = new StringBuffer();

        fieldName = fieldName.toLowerCase();
        String[] fields = fieldName.split("_");
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (i == 0) {
                result.append(field);
            } else {
                result.append(toUpString(field));
            }
        }
        return result.toString();
    }

    /**
     * 首字母大写
     *
     * @param className
     * @return
     */
    public static String toUpString(String className) {
        if(className==""){
            return "";
        }
        char[] cs = className.toCharArray();
        if(cs[0]>96 && cs[0]<123){
            cs[0] -= 32;
           className = String.valueOf(cs);
        }
        return className;
    }

    /**
     * 首字母小写
     *
     * @param className
     * @return
     */
    public static String toLowString(String className) {
        if(className==""){
            return "";
        }
        char[] cs = className.toCharArray();
        if(cs[0]>64 && cs[0]<91){
            cs[0] += 32;
            className = String.valueOf(cs);
        }
        return className;
    }


    /**
     * 数据库类型转属性类型
     * @param columnType
     * @return
     */
    public static String columnType2FieldType(String columnType) {
        String result = dataTypeMap.get(columnType);
        if(isEmpty(result)){
            throw new RuntimeException("数据库字段映射类型需要补齐,:"+columnType);
        }
        return result;
    }

    public static boolean isEmpty(String value) {
        if (null == value || value.equals("")) {
            return true;
        }
        return false;
    }


}
