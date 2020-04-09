package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.service.HelloService;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @Autowired(required=false)
    private HelloService helloService;

    @RequestMapping("/t1")
    public void t1(){
        log.info("LOG  OK");
        System.out.println("OK");
    }

    @PostMapping("/t2")
    public ResultVo t2(){
        return ResultVo.success();
    }
}
