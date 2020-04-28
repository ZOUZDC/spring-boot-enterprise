package zdc.enterprise.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zdc.enterprise.Dao.StudentRepository;
import zdc.enterprise.entity.Student;
import zdc.enterprise.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(long id) {
      studentRepository.deleteById(id);
    }

    @Override
    public Student findById(long id) {
        Optional<Student> one = studentRepository.findById(id);
        if (one.isPresent()) {
            return one.get();
        }
        return null;
    }

    @Override
    public Page<Student> findAll(PageRequest page) {
        page= page!=null?page:PageRequest.of(1, 10);
        return studentRepository.findAll(page);
    }

    @Override
    public void Custom() {
        Student student = new Student();
        student.setName("张");
        List<Student> list = studentRepository.findByNameLike("zhangsan");
        log.info("自定义查询{}",list);
        list = studentRepository.findByNameLikeAndAge("zhangsan",18);
        log.info("自定义查询{}",list);
        list = studentRepository.getListByName("zhangsan");
        log.info("自定义查询{}",list);
    }


}
