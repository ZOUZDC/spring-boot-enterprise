package zdc.enterprise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    //主键id
    private Long id;

    //姓名
    private String name;

    //年龄
    private Integer age;

    //出生日期
    private LocalDate birthdate;

    //零花钱
    private BigDecimal money;


}
