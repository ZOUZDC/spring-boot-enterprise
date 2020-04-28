package zdc.enterprise.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zdc.enterprise.entity.Student;
import zdc.enterprise.mapper.StudentMapper;
import zdc.enterprise.service.StudentService;


@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired(required = false)
    private StudentMapper studentMapper;


    @Override
    public void savePlus(Student vo) {
        studentMapper.insert(vo);
        log.info("Plus.....{}",vo);
        int i = 2/0;
        vo.setId(null);
        studentMapper.insert(vo);
        log.info("Plus1.....{}",vo);
    }
}
