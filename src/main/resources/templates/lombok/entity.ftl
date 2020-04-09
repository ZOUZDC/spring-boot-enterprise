package ${interfaceName?lower_case};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${sysFieldTableNameUp} {

<#list sysParams as param>
    // ${param.fieldNote}
    private ${param.fieldType} ${param.fieldName};

</#list>

}
