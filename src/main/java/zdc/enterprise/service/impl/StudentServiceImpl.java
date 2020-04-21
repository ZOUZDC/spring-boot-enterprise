package zdc.enterprise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zdc.enterprise.constants.Page;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;
import zdc.enterprise.mapper.StudentMapper;
import zdc.enterprise.service.StudentService;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudentByParams(StudentDto studentDto) {
        return studentMapper.getStudentByParams(studentDto);
    }

    @Override
    public List<Student> getStudentPageList(StudentDto studentDto) {
        return studentMapper.getStudentPage(studentDto);
    }

    @Override
    public Page<Student> getStudentPage(StudentDto studentDto, Page page) {

        List<Student> list = studentMapper.getStudentPage(studentDto);
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
}
