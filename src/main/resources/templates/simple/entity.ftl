package ${interfaceName?lower_case};


public class ${sysFieldTableNameUp} {

<#list sysParams as param>
	// ${param.fieldNote}
    private ${param.fieldType} ${param.fieldName};

</#list>
<#list sysParams as param>
	public void set${param.fieldName?cap_first}(${param.fieldType} ${param.fieldName}){
        this.${param.fieldName} = ${param.fieldName};
    }

    public ${param.fieldType} get${param.fieldName?cap_first}(){
        return this.${param.fieldName};
    }

</#list>
}