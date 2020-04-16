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
public class StudentSimple1Dto {


    //info
    @ApiParam("info")
    private String info;

    //remark
    @ApiParam("remark")
    private String remark;

    private String name;
    private LocalDate birthdate;

}
