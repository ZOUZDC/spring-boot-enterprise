package zdc.enterprise.constants;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Random;

@Component
public class MyFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){


        //通过此方式返回的异常数据的http状态码是500   DispatcherType.ERROR 需要处理
       // throw new CustomException("通过MyFilter,抛异常返回值");

    }

    @Override
    public void destroy() {

    }

}
