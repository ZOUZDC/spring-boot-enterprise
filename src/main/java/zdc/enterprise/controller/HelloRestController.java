package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;

@RestController
@Slf4j
@RequestMapping("/hello")
public class HelloRestController {


    @GetMapping("/test")
    public ResultVo test() {
        return ResultVo.success();
    }


}
