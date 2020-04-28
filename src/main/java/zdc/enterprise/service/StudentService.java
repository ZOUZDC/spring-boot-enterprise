package zdc.enterprise.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import zdc.enterprise.entity.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    void deleteById(long id);

    Student findById(long id);

    Page<Student> findAll(PageRequest page);

    void Custom();
}
