package zdc.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import zdc.enterprise.constants.DtoDelete;
import zdc.enterprise.constants.DtoSave;
import zdc.enterprise.constants.DtoUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupDto {

    //主键id
    @NotNull(message = "缺少id的信息", groups = {DtoUpdate.class, DtoDelete.class})
    @Null(message = "id必须为空", groups = { DtoSave.class})
    private Long id;

    //姓名
    @NotEmpty(message = "姓名不能为空", groups = DtoSave.class)
    @Pattern(regexp ="^([\\u4e00-\\u9fa5]+|[a-zA-Z.\\s]+)$", message = "姓名格式有误")
    @Length(min =2, message = "姓名长度应大于{min}位")
    @Length(max =10, message = "姓名长度应小于{max}位")
    private String name;

    //年龄
    @NotNull(message = "年龄不能为空", groups = DtoSave.class)
    @Range(min = 0,max = 150, message = "年龄应在{min}~{max}之间")
    private Integer age;

    //出生日期
    @NotNull(message = "出生日期不能为空", groups = DtoSave.class)
    private LocalDate birthdate;

    //零花钱
    private BigDecimal money;


}
