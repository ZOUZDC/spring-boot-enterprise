package ${basePackage}.${serviceImplName};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${basePackage}.${entityName}.${sysFieldTableName};
import ${basePackage}.${mapperName}.${sysFieldTableName}Mapper;
import ${basePackage}.${serviceName}.${sysFieldTableName}Service;


@Service
@Slf4j
public class ${sysFieldTableName}ServiceImpl extends ServiceImpl<${sysFieldTableName}Mapper, ${sysFieldTableName}> implements ${sysFieldTableName}Service {

    @Autowired(required = false)
    private ${sysFieldTableName}Mapper ${sysFieldTableName?uncap_first}Mapper;



}
