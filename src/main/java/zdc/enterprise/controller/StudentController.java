package zdc.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.Dto;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;
import zdc.enterprise.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired(required = false)
    public StudentService studentService;

    @GetMapping("getById")
    public ResultVo getStudentById(@Validated(Dto.Update.class) StudentDto studentDto){
        Student student = studentService.getById(studentDto.getId());
        return ResultVo.success(student);
    }

    @PostMapping("save")
    public ResultVo saveOne(@Validated(Dto.Save.class) StudentDto studentDto , Student student){
        studentService.save(student);
        return ResultVo.success(student);
    }

    @PostMapping("update")
    public ResultVo updateOne(@Validated(Dto.Update.class) StudentDto studentDto , Student student){
        studentService.updateById(student);
        return ResultVo.success(student);
    }

    @PostMapping("delete")
    public ResultVo deleteOne(@Validated(Dto.Delete.class) StudentDto studentDto ){
        studentService.removeById(studentDto.getId());
        return ResultVo.success();
    }

}
