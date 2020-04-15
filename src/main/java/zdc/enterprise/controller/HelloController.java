package zdc.enterprise.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentSimple1Dto;
import zdc.enterprise.dto.StudentSimpleDto;
import zdc.enterprise.entity.Student;

import java.util.Date;

@RestController
@Slf4j
@Api(tags = "测试")
public class HelloController {

    @ApiOperation(value="一个参数", notes="一个参数的swagger例子,入参必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "one",value = "第一个参数" ,required = true)
    })
    @GetMapping("/oneParams")
    public ResultVo oneParams(String one){
        log.info("请求值---{}",one);
        return ResultVo.success(one);
    }


    @ApiOperation(value="多个参数的例子", notes="一个参数的swagger例子,入参一个必填另个非必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "one",value = "姓名" ,required = true),
            @ApiImplicitParam(name = "two",value = "年龄")
    })
    @GetMapping("/moreParams")
    public ResultVo moreParams(String one,String two){
        log.info("请求值---{},{}",one,two);
        return ResultVo.success("one",one,"two",two);
    }

    @GetMapping("/notParams")
    public ResultVo notParams(){
        return ResultVo.success();
    }

    @ApiOperation(value="传入一个对象", notes="传入对象并解释字段含义")
    @GetMapping("/dto")
    public ResultVo tdtoime(StudentSimpleDto StudentSimpleDto){
        return ResultVo.success(StudentSimpleDto);
    }


    @ApiOperation(value="传入多个对象", notes="传入传入多个对象并解释字段含义,多个dto中存在相同的参数只会显示一个且显示的是第一个的api注释,")
    @GetMapping("/moreDto")
    public ResultVo moreDto(StudentSimpleDto StudentSimpleDto, StudentSimple1Dto StudentSimpleDto1){
        return ResultVo.success("",StudentSimpleDto,"",StudentSimpleDto1);
    }
  @ApiOperation(value="传入多个对象", notes="传入传入多个对象并解释字段含义,多个dto中存在相同的参数只会显示一个且显示的是第一个的api注释,")
    @GetMapping("/moreDto2")
    public ResultVo moreDto2(StudentSimpleDto StudentSimpleDto, Student student){
        return ResultVo.success("",StudentSimpleDto,"",student);
    }



}
