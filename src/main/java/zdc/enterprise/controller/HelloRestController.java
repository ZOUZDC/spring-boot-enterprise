package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.ResultVo;

@RestController
@Slf4j
@RequestMapping("/rest")
public class HelloRestController {


    @PostMapping("/token")
    public ResultVo token(String token, String user ,String trimStr){
        log.info("token {}  , user {} , trimStr:{}:",token,user,trimStr);
        return ResultVo.success("token",token,"user",user,"trimStr",trimStr);
    }

}
