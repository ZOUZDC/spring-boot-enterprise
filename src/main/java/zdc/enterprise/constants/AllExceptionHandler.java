package zdc.enterprise.constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //此注解表示开启了全局异常捕获 返回的信息写入到Response的Body中
//@ControllerAdvice //此注解表示开启了全局异常捕获
@Slf4j
public class AllExceptionHandler {


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




}
