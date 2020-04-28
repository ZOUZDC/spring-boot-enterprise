package zdc.enterprise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@Entity(name = "student")
public class Student implements Serializable {

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //姓名 如果名称和数据库一致或者驼峰下划线一致则可以不使用Column注解
    @Column(name = "name")
    private String name;

    //年龄
    private Integer age;

    //出生日期
    private LocalDate birthdate;

    //零花钱
    private BigDecimal money;

    @Transient  //该值不参与持久化
    private String other;


}
