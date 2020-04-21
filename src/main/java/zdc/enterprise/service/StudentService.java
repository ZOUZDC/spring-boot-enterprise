package zdc.enterprise.service;

import zdc.enterprise.constants.Page;
import zdc.enterprise.dto.StudentDto;
import zdc.enterprise.entity.Student;

import java.util.List;

public interface StudentService {
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
    List<Student> getStudentPageList(StudentDto studentDto);

    /***
     * 分页查询总数
     * @param studentDto
     * @return
     */
    Long getStudentPageCount(StudentDto studentDto);


    /***
     * 分页查询Page
     * @param studentDto
     * @param page
     * @return
     */
    Page<Student> getStudentPage(StudentDto studentDto, Page page);

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

    /***
     * Service异常判断
     * @param student
     * @return
     */
    int saveServiceException(Student student);

    /***
     * Service异常判断 try catch
     * @param student
     * @return
     */
    int saveServiceTryException(Student student);

    int saveServiceTryException2(Student student);

    int saveServiceOtherTryException(Student student);

}
