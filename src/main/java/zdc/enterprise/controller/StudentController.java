package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.entity.Student;
import zdc.enterprise.service.StudentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/st")
public class StudentController {


    @Autowired(required = false)
    private StudentService studentService;

    /**
     * 无id则新增 有id则更新
     * @return
     */
    @PostMapping("/t1")
    public ResultVo t1(){
        Student student = new Student();
        student.setName("张三");
        Student save = studentService.save(student);
        save.setName("李四");
        studentService.save(save);
        return ResultVo.success(save);
    }

    /**
     * 当deleteById的id为空会报错
     * @return
     */
    @PostMapping("/t2")
    public ResultVo t2(){
        Student student = new Student();
        student.setName("张三");
        Student save = studentService.save(student);

        Student one = studentService.findById(student.getId());
        log.info("--1---{}",one);
        studentService.deleteById(student.getId());
        studentService.deleteById(student.getId());

        return ResultVo.success();
    }


    /**
     * findById数据为空时 不会报错
     * @return
     */
    @PostMapping("/t3")
    public ResultVo t3(){
        Student student = new Student();
        student.setName("张三");
        Student save = studentService.save(student);

        Student one = studentService.findById(student.getId());
        log.info("--1---{}",one);
        studentService.deleteById(student.getId());
        Student two = studentService.findById(student.getId());
        log.info("--2---{}",two);
        return ResultVo.success();
    }

    /**
     * 分页查询所有数据
     * @return
     */
    @PostMapping("/t4")
    public ResultVo t4(){
        Page<Student> list = studentService.findAll(PageRequest.of(2,10));
        return ResultVo.success(list);
    }

    /**
     * 根据条件查询数据
     * @return
     */
    @PostMapping("/t5")
    public ResultVo t5(){
        studentService.Custom();
        return ResultVo.success("查看log");
    }

}
