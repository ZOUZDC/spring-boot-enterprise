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
public class AllExceptionHandler{

    @ExceptionHandler(value =CustomException.class)
    public ResultVo customExceptionHandle(Exception e){
        //这个地方应该去创建多个Exception类然后分类去捕获
        log.error("自定义异常: {}",e);
        return ResultVo.fail(e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public ResultVo exceptionHandle(Exception e){
        log.error("未知的异常{}", e);
        //三张格式根据喜好选座位
        //return ResultVo.sysFail(e.getClass().getName());
        //return ResultVo.sysFail("系统异常",e.getClass().getName());
        return ResultVo.sysFail("系统异常",e.getClass().getName(),e.getStackTrace().length>0?e.getStackTrace()[0]:"");

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
