package zdc.enterprise.mapper;

import zdc.enterprise.constants.Page;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;

import java.util.List;

//吐血推荐 IDEA使用 free mybatis plugin 插件 能将 接口类和mapper文件自动关联,自动跳转

public interface StudentMapper {
    /***
     * 根据参量查询student
     * @param studentDto
     * @return
     */
    List<Student> getStudentByParams(StudentDto studentDto);

    /***
     * 分页查询
     * @param studentDto
     * @return
     */
    List<Student> getStudentPage(StudentDto studentDto, Page page);

    /***
     * 分页查询总数
     * @param studentDto
     * @return
     */
    Long getStudentPageCount(StudentDto studentDto);

    /***
     * 新增student
     * @param student
     * @return
     */
    Long saveStudent(Student student);

    /***
     * 根据id更新student
     * @param student
     * @return
     */
    int updateStudentById(Student student);

    /***
     * 根据id删除tudent
     * @param id
     * @return
     */
    int deleteStudentById(Long id);

}