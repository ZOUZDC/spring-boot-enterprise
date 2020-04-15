package zdc.enterprise.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSimpleDto {

    //主键id
    @ApiParam("id")
    private Long id;

    //姓名
    @ApiParam("姓名")
    @Pattern(regexp ="^([\\u4e00-\\u9fa5]+|[a-zA-Z.\\s]+)$", message = "姓名格式有误")
    @Length(min =2, message = "姓名长度应大于{min}位")
    @Length(max =10, message = "姓名长度应小于{max}位")
    private String name;

    //年龄
    @ApiParam("年龄")
    @Range(min = 0,max = 150, message = "年龄应在{min}~{max}之间")
    private Integer age;

    //出生日期
    @ApiParam("出生日期")
    private LocalDate birthdate;

    //零花钱
    @ApiParam(value = "零花钱",required = false)
    private BigDecimal money;


}
