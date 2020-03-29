package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.ResultVo;

@Controller
@Slf4j
@RequestMapping("/s")
public class HelloController {

    @GetMapping("/sayHello")
    public ResultVo getSayHello(String name){
        log.info("请求值---{}",name);
        if(name ==null){
            throw new CustomException("name is null");
        }
        return ResultVo.success(String.format("%s HELLLO", name));
    }

}
