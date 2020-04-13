package zdc.enterprise.constants;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Component
@Order(10)
@WebFilter(filterName = "ParamsFilter", urlPatterns = "/**")
@Slf4j
public class ParamsFilter implements Filter {


    public static List<String> excludePathPatterns =new ArrayList<>(Arrays.asList(
            "/user/login","/user/changePwdById","/static/","/monitor","commonError"
    ));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //一个简陋的过滤
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String url = req.getRequestURI();
        if(checkExcludeUrl(url)|| "/".equals(url)){
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }


        //添加token 删除user
        ParameterRequestWrapper request = new ParameterRequestWrapper(req);

        Map map = new LinkedHashMap<>(req.getParameterMap());
        map.put("token",new String[]{"token1"});
        map.remove("user");

        request.setParameterMap(map);

        filterChain.doFilter(request, servletResponse);
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
