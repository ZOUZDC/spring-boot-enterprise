package zdc.enterprise.constants;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 登录拦截器
 */

@Component
@Order(-999)
public class MyHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest rq, HttpServletResponse response, Object handler ) throws Exception {

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest rq, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
