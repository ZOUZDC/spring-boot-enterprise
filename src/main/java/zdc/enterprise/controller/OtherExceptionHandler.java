package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class OtherExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public ResultVo otherError(HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        String errorMsg = "出现错误";

        if (statusCode == null) {
            errorMsg = "服务器内部错误";
        }
        //针对MyFilter中的转发异常
        if(statusCode==5001){
            log.warn("Filter异常:{}",request.getAttribute("javax.servlet.error.status_msg"));
            return ResultVo.fail(""+request.getAttribute("javax.servlet.error.status_msg"));
        }
        //针对MyFilter中的CustomException异常
        if(statusCode==500 && request.getAttribute("javax.servlet.error.exception") instanceof CustomException){
            log.warn("Filter异常:{}",((Exception)request.getAttribute("javax.servlet.error.exception")).getMessage());
            return ResultVo.fail(((Exception)request.getAttribute("javax.servlet.error.exception")).getMessage());
        }

        try {
            return ResultVo.sysFail(errorMsg, statusCode, HttpStatus.valueOf(statusCode));
        } catch (Exception ex) {
            errorMsg = "服务器内部错误:" + statusCode;
        }
        log.error("服务器错误,错误码:{}",statusCode);
        return ResultVo.sysFail(errorMsg);
    }
}
