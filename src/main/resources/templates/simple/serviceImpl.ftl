package zdc.enterprise.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zdc.enterprise.entity.${sysFieldTableName};
import zdc.enterprise.mapper.${sysFieldTableName}Mapper;
import zdc.enterprise.service.${sysFieldTableName}Service;


@Service
@Slf4j
public class ${sysFieldTableName}ServiceImpl extends ServiceImpl<${sysFieldTableName}Mapper, ${sysFieldTableName}> implements ${sysFieldTableName}Service {

    @Autowired(required = false)
    private ${sysFieldTableName}Mapper ${sysFieldTableName?uncap_first}Mapper;



}
