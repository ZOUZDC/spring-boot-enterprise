package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;
import zdc.enterprise.service.Student2Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/***
 * 事务测试
 */
@Slf4j
@RestController
@RequestMapping("/transact2")
public class Transaction2Controller {

    @Autowired(required = false)
    private Student2Service student2Service;


    @PostMapping("/saveStudent")
    public ResultVo saveStudent() {
        String uuid = UUID.randomUUID().toString();
        Student student = new Student(null, uuid, 18, LocalDate.now(), null, "controller保存");
        student2Service.saveStudent(student);
        StudentDto read = new StudentDto();
        read.setId(student.getId());
        List<Student> studentInfo = student2Service.getStudentByParams(read);
        log.info("刚才保存的数据是{}", studentInfo);
        return ResultVo.success(student);
    }

    /***
     * controller抛异常不会影响事务
     * @return
     */
    @PostMapping("/saveControllerException")
    public ResultVo saveStudentControllerException() {
        String uuid = UUID.randomUUID().toString();
        Student student = new Student(null, uuid, 18, LocalDate.now(), null, "saveStudentControllerException");
        student2Service.saveStudent(student);
        StudentDto read = new StudentDto();
        read.setId(student.getId());
        List<Student> studentInfo = student2Service.getStudentByParams(read);
        log.info("刚才保存的数据是{}", studentInfo);
        if (2 / 2 == 1) {
            throw new CustomException("saveStudentControllerException");
        }
        return ResultVo.success(student);
    }

    /**
     * 需要在事务中运行@Transactional
     * Service方法中抛出异常则该方法事务回滚
     *
     * @return
     */
    @PostMapping("/saveServiceException")
    public ResultVo saveStudentServiceException() {
        String uuid = UUID.randomUUID().toString();
        Student student = new Student(null, uuid, 18, LocalDate.now(), null, "saveStudentServiceException");
        student2Service.saveServiceException(student);
        return ResultVo.success(student);
    }

    /**
     * 需要在事务中运行@Transactional
     * Service方法中抛出异常则该方法事务回滚
     * 如果方法中存在trycatch则不会回滚 service中123都没有回滚
     *
     * @return
     */
    @PostMapping("/saveServiceTryException")
    public ResultVo saveServiceTryException() {
        String uuid = UUID.randomUUID().toString();
        Student student = new Student(null, uuid, 18, LocalDate.now(), null, "saveServiceTryException");
        student2Service.saveServiceTryException(student);
        return ResultVo.success(student);
    }

    /**
     * 需要在事务中运行@Transactional
     * Service方法中抛出异常则该方法事务回滚
     * 如果方法中存在trycatch则不会回滚 service中都没有回滚
     *
     * @return
     */
    @PostMapping("/saveServiceTryException2")
    public ResultVo saveServiceTryException2() {
        String uuid = UUID.randomUUID().toString();
        Student student = new Student(null, uuid, 18, LocalDate.now(), null, "saveServiceTryException");
        student2Service.saveServiceTryException2(student);
        return ResultVo.success(student);
    }


    /**
     * 需要在事务中运行@Transactional
     * Service中报错.当前方法中的和被调用的方法(即使有trycatch)都会回滚即使
     *
     * @return
     */
    @PostMapping("/saveServiceOtherTryException")
    public ResultVo saveServiceOtherTryException() {
        String uuid = UUID.randomUUID().toString();
        Student student = new Student(null, uuid, 18, LocalDate.now(), null, "saveServiceTryException");
        student2Service.saveServiceOtherTryException(student);
        return ResultVo.success(student);
    }


}
