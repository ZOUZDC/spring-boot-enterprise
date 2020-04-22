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
@Transactional(propagation=Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Student> getStudentByParams(StudentDto studentDto) {
        return studentMapper.getStudentByParams(studentDto);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Student> getStudentPageList(StudentDto studentDto) {
        return studentMapper.getStudentPage(studentDto,new Page().setFenye(true));
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
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
    @Transactional(propagation=Propagation.SUPPORTS)
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


}
