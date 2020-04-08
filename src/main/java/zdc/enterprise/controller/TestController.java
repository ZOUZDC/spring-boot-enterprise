package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.DtoDelete;
import zdc.enterprise.constants.DtoSave;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentGroupDto;
import zdc.enterprise.dto.StudentSimpleDto;
import zdc.enterprise.entity.Student;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {


    /***
     * 没有参数验证
     * @param student
     * @return
     */
    @RequestMapping("/s1")
    public ResultVo getStudent1(Student student){
        log.info("getStudent1 的参数为 {}",student);
        return ResultVo.success();
    }

    /***
     * 模拟查询
     * @param student
     * @param studentSimpleDto
     * @return
     */
    @RequestMapping("/s2")
    public ResultVo getStudent2(Student student, @Valid StudentSimpleDto studentSimpleDto){
        log.info("getStudent2 的参数为 {}",student);
        return ResultVo.success();
    }

    /***
     * 模拟查询
     * s4 和 s2 判断结果相同
     * @param student
     * @param studentGroupDto
     * @return
     */
    @RequestMapping("/s4")
    public ResultVo getStudent4(Student student, @Validated StudentGroupDto studentGroupDto){
        log.info("getStudent4 的参数为 {}",student);
        return ResultVo.success();
    }


    /***
     * 模拟新增 (若在之后设置为null,则可以不判断是否存在主键)
     * @param student
     * @param studentGroupDto
     * @return
     */
    @RequestMapping("/s5")
    public ResultVo getStudent5(Student student, @Validated(DtoSave.class) StudentGroupDto studentGroupDto){
        log.info("getStudent5 的参数为 {}",student);
        return ResultVo.success();
    }
    /***
     * 模拟新增 若在controller绑定BindingResult ,则不会在其他地方抛出异常
     * @param student
     * @param studentGroupDto
     * @return
     */
    @RequestMapping("/s5_1")
    public ResultVo getStudent51(Student student, @Validated(DtoSave.class) StudentGroupDto studentGroupDto, BindingResult ex){
        log.info("getStudent51 的参数为 {} ,异常信息为:{}",student,ex);
        return ResultVo.success();
    }


    /***
     * 模拟删除 必须有主键
     * @param student
     * @param studentGroupDto
     * @return
     */
    @RequestMapping("/s6")
    public ResultVo getStudent6(Student student, @Validated(DtoDelete.class) StudentGroupDto studentGroupDto){
        log.info("getStudent6 的参数为 {}",student);
        return ResultVo.success();
    }

}
