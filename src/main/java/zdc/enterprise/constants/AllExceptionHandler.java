package zdc.enterprise.constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice  //此注解表示开启了全局异常捕获 返回的信息写入到Response的Body中
//@ControllerAdvice @ResponseBody//此注解表示开启了全局异常捕获
@Slf4j
//public class AllExceptionHandler{
public class AllExceptionHandler extends ResponseEntityExceptionHandler {  //ResponseEntityExceptionHandler此类中存在部分异常,适当的考虑重写

    /*@ExceptionHandler(value =org.springframework.validation.BindException.class)
    public ResultVo BindException(BindException e){
        //解析比较麻烦直接切割
        String msg = e.getMessage();
        return ResultVo.fail(msg.substring(msg.lastIndexOf("[")+1,msg.lastIndexOf("]")));
    }

*/

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


    /***
     * 重写入参验证的异常方法,抛出指定格式数据
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String defaultMessage = "参数异常" ;
        try {
            defaultMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.handleExceptionInternal(ex,ResultVo.fail(defaultMessage), headers, status, request);
    }

    public AllExceptionHandler() {
        super();
    }
}
