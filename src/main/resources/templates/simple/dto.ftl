package zdc.enterprise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ${sysFieldTableName}Dto {

<#list sysParams as param>
    // ${param.fieldNote}
    private ${param.fieldType} ${param.fieldName};

</#list>

}
