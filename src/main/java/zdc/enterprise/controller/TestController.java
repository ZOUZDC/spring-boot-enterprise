package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentSimpleDto;
import zdc.enterprise.entity.Student;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/s1")
    public ResultVo getStudent1(Student student){
        log.info("getStudent1 的参数为 {}",student);
        return ResultVo.success();
    }

    @RequestMapping("/s2")
    public ResultVo getStudent2(Student student, @Valid StudentSimpleDto studentSimpleDto){
        log.info("getStudent2 的参数为 {}",student);
        return ResultVo.success();
    }


}
