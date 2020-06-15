package zdc.enterprise.constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;

/**
 * 重写HttpServletRequestWrapper 某些方法来允许入参可以修改,及去除空格
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> parameterMap; // 所有参数的Map集合

    public ParameterRequestWrapper(HttpServletRequest request) {
        super(request);
        parameterMap = request.getParameterMap();
    }

    // 重写几个HttpServletRequestWrapper中的方法

    /**
     * 获取所有参数名
     *
     * @return 返回所有参数名
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<String>(parameterMap.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name 指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        if(results!=null && results[0]!=null){
            return results[0].trim();
        }
        return  null;
    }


    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] strings = parameterMap.get(name);
        if(strings!=null &&strings.length>0){
            for (int i = 0; i <strings.length ; i++) {
                strings[i]=strings[i].trim();
            }
        }
        return strings;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }






}
