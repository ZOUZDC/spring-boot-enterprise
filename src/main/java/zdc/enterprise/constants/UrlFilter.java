package zdc.enterprise.constants;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * url过滤器  ,静态资源直接略过, 无需权限控制的直接略过
 * 也可以在这里做跨域
 */
@Component
//数值小的在前面
@Order(0)
@WebFilter(filterName = "UrlFilter", urlPatterns = "/**")
@Slf4j
public class UrlFilter implements Filter {


    //忽略的url
    public static List<String> excludePathPatterns =new ArrayList<>(Arrays.asList(
            "/user/login",
            "/user/changePwdById",
            "/common/",
            "/monitor"
    ));

    //静态资源文件
    private static List<String> staticPathPatterns =new ArrayList<>(Arrays.asList(
            "/static",
            "/webJar/"

    ));

    //转发或者重定向的资源

    //配置的静态资源路径需要在MyMvcConfig 添加
    private static Map<String,String> replacePathMap =new HashMap();
    static{
        replacePathMap.put("/","/webJar");
        replacePathMap.put("/favicon.ico","/webJar/favicon.ico");
    }


    @Value("${server.servlet.context-path:}")
    private String contextPath = "";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        //如果配置了context-path 则去掉
        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if(contextPath!=null&& !"".equals(contextPath)){
            url = url.substring(contextPath.length());
        }
        log.info("filter.info1");

        //静态资源文件不拦截
        for (String pathPattern : staticPathPatterns) {
            if(Objects.equals(pathPattern, url)){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        log.info("filter.info2");


        //需要转发或者重定向的资源 ,这里根据需要2选一
        for (Map.Entry<String, String> entry : replacePathMap.entrySet()) {
            if(Objects.equals(entry.getKey(), url)){
                //重定向
                ((HttpServletResponse)servletResponse).sendRedirect(entry.getValue());
                //转发
                //servletRequest.getRequestDispatcher(entry.getValue()).forward(servletRequest, servletResponse);
                return;
            }
        }

        // 无需拦截的url
        for (String pattern : excludePathPatterns) {
            if (url.contains(pattern)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        //添加属性,剩下的需要 做权限判断
        servletRequest.setAttribute("xFilter","needMyFilter");
        filterChain.doFilter(servletRequest, servletResponse);

    }

}
