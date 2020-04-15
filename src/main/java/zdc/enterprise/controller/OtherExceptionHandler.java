package zdc.enterprise.controller;

import io.swagger.annotations.Api;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@ApiIgnore  //此接口不在文档中显示
@RestController
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
            return ResultVo.sysFail("服务器未知的内部错误");
        }

        //针对MyFilter中的CustomException异常
        if(statusCode==500 && request.getAttribute("javax.servlet.error.exception") instanceof CustomException){
            return ResultVo.fail(((Exception)request.getAttribute("javax.servlet.error.exception")).getMessage());
        }

        try {
            return ResultVo.sysFail(errorMsg, statusCode, HttpStatus.valueOf(statusCode));
        } catch (Exception ex) {
            errorMsg = "服务器内部错误:" + statusCode;
        }
        return ResultVo.sysFail(errorMsg);
    }
}
