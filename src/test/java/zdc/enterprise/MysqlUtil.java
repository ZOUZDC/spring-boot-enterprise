package zdc.enterprise;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlUtil {
	private static String DRIVER_CLASS ;
    private static String DATABASE_URL ;
    private static String DATABASE_USER ;
    private static String DATABASE_PASSWORD ;
    private static Connection con = null;
    private static DatabaseMetaData dbmd = null;

    /**
     * 初始化数据库链接
     */
    public static boolean init(String driverClass, String dbURL, String userName, String passWord){
    	try {
    		DATABASE_URL =  dbURL;
    		DATABASE_USER = userName;
    		DATABASE_PASSWORD = passWord;
            DRIVER_CLASS = driverClass;

    		Class.forName(DRIVER_CLASS);
    		con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    		dbmd = con.getMetaData();
    		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }

    

    /**
     * 获取表字段信息
     * @param tableName
     * @return
     */
    public static List<Map<String,String>> getTableCloumns(String tableName){

        String database =DATABASE_URL.substring(DATABASE_URL.lastIndexOf("/")+1);
        database= database.split("\\?")[0];

        List<Map<String,String>> columns = new ArrayList<Map<String,String>>();
    	try{
            if (con.isClosed()) {
                con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            }
            Statement stmt = con.createStatement();
             
            String sql = "select column_name, data_type, column_key, is_nullable, column_comment from information_schema.columns where table_name='" + tableName + "'and table_schema='" + database+ "' ORDER BY ORDINAL_POSITION ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                HashMap<String,String> map = new HashMap<String,String>();
                map.put("columnName", rs.getString("column_name"));
                map.put("dataType", rs.getString("data_type"));
                map.put("isKey", TempUtil.isEmpty(rs.getString("column_key"))?"false":"true");
                map.put("notNull", rs.getString("is_nullable").equals("YES")?"false":"true");
                map.put("comment", rs.getString("column_comment"));
                columns.add(map);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally{
        	if(null != con){
	            try {
	        		con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
		return columns;
    }


}
