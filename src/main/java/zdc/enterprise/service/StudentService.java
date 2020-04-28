package zdc.enterprise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zdc.enterprise.entity.Student;


public interface StudentService extends IService<Student> {


    void savePlus(Student vo);
}
