package zdc.enterprise.constants;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 登陆验证根据需要在ParamsFilter或者MyHandlerInterceptor中做
 */
@Component
//数值小的在前面
@Order(10)
@WebFilter(filterName = "ParamsFilter", urlPatterns = "/**")
@Slf4j
public class ParamsFilter implements Filter {


    public static List<String> excludePathPatterns =new ArrayList<>(Arrays.asList(
            "/user/login","/user/changePwdById","/static/","/monitor","commonError"
    ));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println(url);
        System.out.println("ParamsFilter");
        filterChain.doFilter(servletRequest, servletResponse);
        return ;

    }



}
