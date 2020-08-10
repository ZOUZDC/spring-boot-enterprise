package ${basePackage}.${controllerName};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${basePackage}.constants.Dto;
import ${basePackage}.constants.ResultVo;
import ${basePackage}.${dtoName}.${sysFieldTableName}Dto;
import ${basePackage}.${entityName}.${sysFieldTableName};
import ${basePackage}.${serviceName}.${sysFieldTableName}Service;

@Slf4j
@RestController
@RequestMapping("/${sysFieldTableName?uncap_first}")
public class ${sysFieldTableNameSuffix} {

    @Autowired(required = false)
    private ${sysFieldTableName}Service ${sysFieldTableName?uncap_first}Service;

    @GetMapping("getById")
    public ResultVo get${sysFieldTableName}ById(@Validated(Dto.Id.class) ${sysFieldTableName}Dto ${sysFieldTableName?uncap_first}Dto , ${sysFieldTableName} ${sysFieldTableName?uncap_first}){
     ${sysFieldTableName?uncap_first} = ${sysFieldTableName?uncap_first}Service.getById(${sysFieldTableName?uncap_first}.getId());
    return ResultVo.success(${sysFieldTableName?uncap_first} );
    }


}