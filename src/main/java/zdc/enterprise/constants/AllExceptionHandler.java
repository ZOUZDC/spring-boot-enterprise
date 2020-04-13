package zdc.enterprise.constants;

import com.sun.prism.impl.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.concurrent.Executor;

@RestControllerAdvice //此注解表示开启了全局异常捕获 返回的信息写入到Response的Body中
//@ControllerAdvice //此注解表示开启了全局异常捕获
@Slf4j
//public class AllExceptionHandler implements ResponseBodyAdvice {
public class AllExceptionHandler{
//public class AllExceptionHandler extends ResponseEntityExceptionHandler {  //ResponseEntityExceptionHandler此类中存在部分异常,适当的考虑重写


    @ExceptionHandler(value =CustomException.class)
    public ResultVo customExceptionHandle(Exception e){
        log.info("------------------自定义异常---------------------'");
        return ResultVo.fail(e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public ResultVo exceptionHandle(Exception e){
        log.info("------------------未知的异常---------------------'");
        return ResultVo.sysFail(e.getMessage());

    }



    public AllExceptionHandler() {
        super();
    }

   /* @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //是否拦截
        return true;
    }
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(body instanceof ResultVo){
            return body;
        }
        //需要根据body数据来区分是正常还是异常
        return ResultVo.success(body);
    }*/
}
