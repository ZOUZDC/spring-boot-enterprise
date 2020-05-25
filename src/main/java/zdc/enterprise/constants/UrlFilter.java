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
@Order(9)
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
    public static List<String> webJarPathPatterns =new ArrayList<>(Arrays.asList(
            "/static",
            "/webJar/"

    ));

    //转发或者重定向的资源
    public static List<String> replacePathPatterns =new ArrayList<>(Arrays.asList(
            "/static11",
            "/webjar/"

    ));

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        if(contextPath!=null){
            url = url.substring(contextPath.length());
        }

        //转发重定向到其他路径
        if("/webjar/webjar".equals(url)){
            //重定向
            ((HttpServletResponse)servletResponse).sendRedirect("/hello/test");
            //转发
            servletRequest.getRequestDispatcher("/web/favicon.ico").forward(servletRequest, servletResponse);
            return;
        }





        filterChain.doFilter(servletRequest, servletResponse);
    }



    //检查是否是需要过滤的连接
    private boolean checkExcludeUrl(String requestURI) {
        for (String pattern : excludePathPatterns) {
            if (requestURI!=null && requestURI.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

}
