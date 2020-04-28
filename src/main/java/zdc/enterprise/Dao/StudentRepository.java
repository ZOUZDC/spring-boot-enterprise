package zdc.enterprise.Dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zdc.enterprise.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAllByNameLike(PageRequest page);

    List<Student> findByNameLike(String name);
    List<Student> findByNameLikeAndAge(String name,int age);

    @Query("select s from student s where s.name =:name ")
    List<Student> getListByName(@Param("name")String name);

}
