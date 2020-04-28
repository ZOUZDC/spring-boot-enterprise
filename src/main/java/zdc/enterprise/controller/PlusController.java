package zdc.enterprise.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/plus")
@Slf4j
public class PlusController {

    @Autowired(required = false)
    public StudentService studentService;

    /***
     * 具体请访问官方文档https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
     * @return
     */
    @GetMapping("find")
    public ResultVo getStudentById(){

        Student vo =new  Student();
        vo.setMoney(new BigDecimal(12));
        vo.setName("测试");
        vo.setAge(18);
        vo.setInfo("121212");
        studentService.save(vo);
        // eq  SELECT id,name,age,birthdate,money,info FROM student WHERE (id = ? AND age = ?)
        List<Student> list = studentService.list(new QueryWrapper<Student>().lambda().eq(Student::getId, vo.getId()).eq(Student::getAge, vo.getAge()));
        log.info("----根据属相eq查询--{}",list.size());
        // or  SELECT id,name,age,birthdate,money,info FROM student WHERE (id = ? AND age <> ?)
        list =  studentService.list(new QueryWrapper<Student>().lambda().eq(Student::getId, vo.getId()).ne(Student::getAge,vo.getAge()));
        log.info("----根据属相or查询--{}",list.size());
        // gt lt  SELECT id,name,age,birthdate,money,info FROM student WHERE (age > ? AND age < ?)
        list =studentService.list(new QueryWrapper<Student>().lambda().gt(Student::getAge,14).lt(Student::getAge,20));
        log.info("----根据属相gt lt查询--{}",list.size());
        // like  SELECT id,name,age,birthdate,money,info FROM student WHERE (info LIKE ?)
        list =studentService.list(new QueryWrapper<Student>().lambda().like(Student::getInfo,"12"));
        log.info("----根据属相like查询--{}",list.size());
        // or  SELECT id,name,age,birthdate,money,info FROM student WHERE (age = ? OR name = ?)
        list =studentService.list(new QueryWrapper<Student>().lambda().eq(Student::getAge,vo.getAge()).or().eq(Student::getName,vo.getName()));
        //  OR 嵌套  SELECT id,name,age,birthdate,money,info FROM student WHERE ((age = ? OR name = ?))
        list =studentService.list(new QueryWrapper<Student>().lambda().and(i->i.eq(Student::getAge,vo.getAge()).or().eq(Student::getName,vo.getName())));
        log.info("----根据属相or查询--{}",list.size());


        return ResultVo.success(vo);
    }

    @PostMapping("save")
    public ResultVo saveOne(){

        Student vo =new  Student();
        vo.setMoney(new BigDecimal(12));
        vo.setName("测试");
        vo.setAge(18);
        vo.setInfo("plus");
        studentService.save(vo);
        studentService.savePlus(vo);
        return ResultVo.success(vo);
    }


}
