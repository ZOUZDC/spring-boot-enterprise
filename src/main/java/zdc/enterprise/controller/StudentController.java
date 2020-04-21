package zdc.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.Dto;
import zdc.enterprise.constants.Page;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;
import zdc.enterprise.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired(required = false)
    private StudentService studentService;

    //对于最基本的增删改查来说, 同时注入 dto 和 entity对象 可以很方便的在dto做验证 ,对entity操作.
    //且dto可以传入entity中没有的参数做查询操作

    //不要给我说用restful风格...


    @GetMapping("/getStudentById")
    public ResultVo getStudentById(@Validated StudentDto studentDto){
        List<Student> list = studentService.getStudentByParams(studentDto);
        return ResultVo.success(list.size()>0?list.get(0):null);
    }
    @PostMapping("/getStudentByParams")
    public ResultVo getStudentByParams(@Validated StudentDto studentDto ){
        List<Student> list = studentService.getStudentByParams(studentDto);
        return ResultVo.success(list);
    }

    @PostMapping("/getStudentPage")
    public ResultVo getStudentPage(@Validated StudentDto studentDto , Student student, Page page ){

       /* List<Student> list = studentService.getStudentPageList(studentDto);
        Long count =0L;
        if(list.size()!=0){
            count = studentService.getStudentPageCount(studentDto);
        }
         return ResultVo.success("dataList",list,"count",count);*/

        Page<Student> resultPage=studentService.getStudentPage(studentDto,page);

        return ResultVo.success("dataList",resultPage.getList(),"count",resultPage.getTotal());
    }

    @PostMapping("/saveStudent")
    public ResultVo saveStudent(@Validated({Dto.Save.class}) StudentDto studentDto , Student student ){
        studentService.saveStudent(student);
        return ResultVo.success(student);
    }

    @PostMapping("/updateStudentById")
    public ResultVo updateStudentById(@Validated({Dto.Update.class}) StudentDto studentDto , Student student ){
        studentService.updateStudentById(student);
        return ResultVo.success();
    }

    @PostMapping("/deleteStudentById")
    public ResultVo deleteStudentById(@Validated({Dto.Delete.class}) StudentDto studentDto){
        studentService.deleteStudentById(studentDto.getId());
        return ResultVo.success();
    }
}
