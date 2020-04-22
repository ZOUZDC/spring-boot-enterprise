package zdc.enterprise.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.Dto;
import zdc.enterprise.constants.Page;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;
import zdc.enterprise.service.StudentService;
import zdc.enterprise.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/***
 * 注解式事务 测试
 * 测试此类时请先禁用MyTransactionConfig 该配置
 * 当前事务属性均为REQUIRED ,如果不同则会有其他不一样的结果
 */
@Slf4j
@RestController
@RequestMapping("/t1")
public class TransactionController {

    private Student getOneStudent(String info){
        return new Student(null,UUID.randomUUID().toString(),18,null,null,info);
    };

    @Autowired
    private TransactionService transactionService;

    /***
     * 直接报错
     * 结果:事务回滚
     * @return
     */
    @PostMapping("/t1")
    public ResultVo t1(){
        transactionService.t1(getOneStudent(""),getOneStudent(""),getOneStudent(""));
        return ResultVo.success();
    }

    /***
     * try catch 异常不抛出 ,
     * 结果:事务不回滚,数据插入成功
     * @return
     */
    @PostMapping("/t2")
    public ResultVo t2(){
        Student oneStudent = getOneStudent("");
        transactionService.t2(getOneStudent(""),getOneStudent(""),getOneStudent(""));
        return ResultVo.success(oneStudent);
    }

    /***
     * try catch 异常 抛出
     * 结果:事务回滚
     * @return
     */
    @PostMapping("/t3")
    public ResultVo t3(){
        Student oneStudent = getOneStudent("");
        transactionService.t3(getOneStudent(""),getOneStudent(""),getOneStudent(""));
        return ResultVo.success(oneStudent);
    }


    /***
     * try catch 异常 不抛出 其中一个是调用其他方法保存
     * 结果:事务不回滚,数据插入成功
     * @return
     */
    @PostMapping("/t4")
    public ResultVo t4(){
        Student oneStudent = getOneStudent("");
        transactionService.t4(getOneStudent(""),getOneStudent(""),getOneStudent(""));
        return ResultVo.success(oneStudent);
    }

    /***
     * try catch 异常 抛出 其中一个是调用其他方法保存
     * 结果:事务回滚,其他调用的方法数据也回滚
     * @return
     */
    @PostMapping("/t5")
    public ResultVo t5(){
        Student oneStudent = getOneStudent("");
        transactionService.t5(getOneStudent(""),getOneStudent(""),getOneStudent(""));
        return ResultVo.success(oneStudent);
    }


}
