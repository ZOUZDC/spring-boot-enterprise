package zdc.enterprise.constants;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Random;

@Component
public class MyFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //在这里有两种返回方式, 1.转发或者OtherExceptionHandlerde /error接口 2.抛异常然后通过OtherExceptionHandler返回 3,就是直接在response中写返回值,在前后端项目中需要在此处设置跨域,也恶意单独有一个filter跨域

       //  throw new CustomException("通过MyFilter,抛异常返回值");


    }

    @Override
    public void destroy() {

    }

}
