package zdc.enterprise.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zdc.enterprise.constants.CustomException;
import zdc.enterprise.constants.Page;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;
import zdc.enterprise.mapper.StudentMapper;
import zdc.enterprise.service.StudentService;

import java.util.List;
@Service
@Slf4j
//如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务 ,可以考录在class中使用此注解,然后在只读方法上使用@Transactional(propagation=Propagation.SUPPORTS),或者直接在需要事务的上面使用此注解
//@Transactional(propagation=Propagation.REQUIRED)
//如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行
//@Transactional(propagation=Propagation.SUPPORTS)
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;

    @Override

    public List<Student> getStudentByParams(StudentDto studentDto) {
        return studentMapper.getStudentByParams(studentDto);
    }

    @Override
    public List<Student> getStudentPageList(StudentDto studentDto) {
        return studentMapper.getStudentPage(studentDto,new Page().setFenye(true));
    }

    @Override
    public Page<Student> getStudentPage(StudentDto studentDto, Page page) {

        List<Student> list = studentMapper.getStudentPage(studentDto,page);
        Long count =0L;
        if(list.size()!=0){
            count = studentMapper.getStudentPageCount(studentDto);
        }
        page.setList(list);
        page.setTotal(count);
        return page;
    }

    @Override
    public Long getStudentPageCount(StudentDto studentDto) {
        return studentMapper.getStudentPageCount(studentDto);
    }

    @Override
    public Long saveStudent(Student student) {
        return studentMapper.saveStudent(student);
    }

    @Override
    public int updateStudentById(Student student) {
        return studentMapper.updateStudentById(student);
    }

    @Override
    public int deleteStudentById(Long id) {
        return studentMapper.deleteStudentById(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public int saveServiceException(Student student) {
        Long aLong = this.saveStudent(student);
        log.info("Service报错 {}",student);
        if(2/2==1){
            throw  new CustomException("saveStudentControllerException");
        }
        return 1;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public int saveServiceTryException(Student student) {

            Long aLong = this.saveStudent(student);
            log.info("Service1报错 {}",student);
        try {
            student.setId(null);
            aLong = this.saveStudent(student);
            log.info("Service2报错 {}",student);
            if(2/2==1){
                throw  new CustomException("saveStudentControllerException");
            }
        }catch (Exception e){

        }
        student.setId(null);
        aLong = this.saveStudent(student);
        log.info("Service3报错 {}",student);
        return 1;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public int saveServiceTryException2(Student student) {
        Long aLong = this.saveStudent(student);
        log.info("Service报错 {}",student);
        try {

            if(2/2==1){
                throw  new CustomException("saveStudentControllerException");
            }
        }catch (Exception e){

        }
        return 1;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public int saveServiceOtherTryException(Student student) {
        this.saveServiceTryException2(student);
        student.setId(null);
        this.saveStudent(student);
        log.info("Service2报错 {}",student);
        if(2/2==1){
            throw  new CustomException("saveStudentControllerException");
        }
        return 0;
    }


}
