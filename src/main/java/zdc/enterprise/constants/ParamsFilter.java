package zdc.enterprise.constants;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Order(2)
@WebFilter(filterName = "ParamsFilter", urlPatterns = "/**")
@Slf4j
public class ParamsFilter implements Filter {




    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        //被urlFiler略过的url 此处也略过
        if(servletRequest.getAttribute("xFilter")==null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        HttpServletRequest req = (HttpServletRequest) servletRequest;

        //添加token 删除user
        ParameterRequestWrapper request = new ParameterRequestWrapper(req);

        Map map = new LinkedHashMap<>(req.getParameterMap());
        map.put("token",new String[]{"token1"});
        map.remove("user");

        request.setParameterMap(map);

        filterChain.doFilter(request, servletResponse);
    }





}
