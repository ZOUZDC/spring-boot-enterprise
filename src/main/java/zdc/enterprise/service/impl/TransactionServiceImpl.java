package zdc.enterprise.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zdc.enterprise.entity.Student;
import zdc.enterprise.mapper.StudentMapper;
import zdc.enterprise.service.TransactionService;


@Service
@Slf4j
@Transactional(propagation= Propagation.REQUIRED)
public class TransactionServiceImpl implements TransactionService {

    @Autowired(required = false)
    private StudentMapper studentMapper;

    /**
     * 多个直接保存
     * @param one
     * @param two
     * @param three
     */
    @Override
    public void t1(Student one, Student two, Student three) {
        studentMapper.saveStudent(one);
        log.info("@@@@@@@@@@@@@保存,主键{}",one.getId());

        studentMapper.saveStudent(two);
        log.info("@@@@@@@@@@@@@保存,主键{}",two.getId());
        int i =5/0;
        studentMapper.saveStudent(three);
        log.info("@@@@@@@@@@@@@保存,主键{}",three.getId());
    }

    /**
     * try catch 异常不抛出
     * @param one
     * @param two
     * @param three
     */
    @Override
    public void t2(Student one, Student two, Student three) {
        studentMapper.saveStudent(one);
        log.info("@@@@@@@@@@@@@保存,主键{}",one.getId());

        try{

            studentMapper.saveStudent(two);
            log.info("@@@@@@@@@@@@@保存,主键{}",two.getId());

            int i =5/0;
        }catch (Exception e){

        }

        studentMapper.saveStudent(three);
        log.info("@@@@@@@@@@@@@保存,主键{}",three.getId());
    }

    /**
     * try catch 异常 抛出
     * @param one
     * @param two
     * @param three
     */
    @Override
    public void t3(Student one, Student two, Student three) {
        studentMapper.saveStudent(one);
        log.info("@@@@@@@@@@@@@保存,主键{}",one.getId());

        try{

            studentMapper.saveStudent(two);
            log.info("@@@@@@@@@@@@@保存,主键{}",two.getId());

            int i =5/0;

        }catch (Exception e){
            throw new RuntimeException("抛异常了");
        }

        studentMapper.saveStudent(three);
        log.info("@@@@@@@@@@@@@保存,主键{}",three.getId());
    }

    /**
     * try catch 异常 不抛出 其中一个是调用其他方法保存
     * @param one
     * @param two
     * @param three
     */
    @Override
    public void t4(Student one, Student two, Student three) {

        save(one);
        try{

            studentMapper.saveStudent(two);
            log.info("@@@@@@@@@@@@@保存,主键{}",two.getId());
            save(three);
            int i =5/0;
        }catch (Exception e){

        }


    }

    /**
     * try catch 异常 抛出 其中一个是调用其他方法保存
     * @param one
     * @param two
     * @param three
     */
    @Override
    public void t5(Student one, Student two, Student three) {

        save(one);
        try{

            studentMapper.saveStudent(two);
            log.info("@@@@@@@@@@@@@保存,主键{}",two.getId());
            save(three);
            int i =5/0;
        }catch (Exception e){
            throw new RuntimeException("抛异常了");
        }
    }



    private void save(Student one){
        studentMapper.saveStudent(one);
        log.info("@@@@@@@@@@@@@保存,主键{}",one.getId());
    }
}
