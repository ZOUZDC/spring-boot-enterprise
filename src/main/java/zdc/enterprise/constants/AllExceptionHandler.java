package zdc.enterprise.constants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice //此注解表示开启了全局异常捕获 返回的信息写入到Response的Body中
@Slf4j
public class AllExceptionHandler{


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

    /*-------------------------系统默认的异常--------------------------*/

    /**
     * 重写入参验证的异常方法
     * @param ex
     * @return
     */
    @ExceptionHandler(value = org.springframework.validation.BindException.class)
    public ResultVo bindExceptionHandle(BindException ex){
        log.info("------------------未知的异常---------------------'");
        StringBuffer defaultMessage = new StringBuffer("参数异常");

        List<ObjectError> list = ex.getBindingResult().getAllErrors();
        if(list!=null && list.size()>0){
            defaultMessage.append(":")  ;
            try {
                int i=0;
                for (ObjectError error : list) {
                    if(i>0){
                        defaultMessage.append(",");
                    }
                    defaultMessage.append(error.getDefaultMessage());

                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ResultVo.fail(defaultMessage.toString());

    }



}
