package zdc.enterprise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdc.enterprise.constants.Dto;
import zdc.enterprise.constants.ResultVo;
import zdc.enterprise.dto.${sysFieldTableName}Dto;
import zdc.enterprise.entity.${sysFieldTableName};
import zdc.enterprise.service.${sysFieldTableName}Service;


@RestController
@RequestMapping("/${sysFieldTableName?uncap_first}")
public class ${sysFieldTableNameSuffix} {

    @Autowired(required = false)
    private ${sysFieldTableName}Service ${sysFieldTableName?uncap_first}Service;

    @GetMapping("getById")
    public ResultVo get${sysFieldTableName}ById(@Validated(Dto.Id.class) ${sysFieldTableName}Dto ${sysFieldTableName?uncap_first}Dto , ${sysFieldTableName} ${sysFieldTableName?uncap_first}){
    ${sysFieldTableName} ${sysFieldTableName?uncap_first} = ${sysFieldTableName?uncap_first}Service.getById(${sysFieldTableName?uncap_first}.getId());
    return ResultVo.success(${sysFieldTableName?uncap_first} );
    }


}