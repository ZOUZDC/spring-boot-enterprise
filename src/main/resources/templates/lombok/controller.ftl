package ${interfaceName?lower_case};

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;

@RestController
@RequestMapping("/${sysFieldTableName?uncap_first}")
@Slf4j
public class ${sysFieldTableNameUp} {

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