package zdc.enterprise.constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 拦截器
 */

@Component
@Order(-999)
@Slf4j
public class MyHandlerInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest rq, HttpServletResponse response, Object handler ) throws Exception {
        log.info("redis hasKey {}",redisTemplate.hasKey("123"));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest rq, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
