<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPath?lower_case}.${sysFieldTableName}Mapper">

    <#--此处的type 需要根据项目填写-->
    <resultMap type="${entityPath?lower_case}.${sysFieldTableName}" id="BaseResultMap">
        <#list sysParams as param>
            <result column="${param.columnName}" property="${param.fieldName}" />
        </#list>
    </resultMap>


    <sql id="Base_Column_List">
        <#list sysParams as param>
            `${param.columnName}` <#if param_has_next>,</#if>
        </#list>
    </sql>

    <!-- 列表查询 -->
    <select id="list" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"></include>
        FROM ${sysTableName}
        WHERE 1=1
        <#list sysParams as param>
            <#if param.fieldType == "String">
                <if test="${param.fieldName} != null and ${param.fieldName} != ''">
                    AND ${param.columnName} like concat ('%', '${r'#{'}${param.fieldName}}' , '%' )   /*请根据情况去判断是= 还是like 并删掉此句*/
                </if>
            <#elseif param.fieldType == "Date">
                <if test="${param.fieldName}Start != null">
                    <![CDATA[ AND ${param.columnName} >= ${r'#{'}${param.fieldName}Start}]]>
                </if>
                <if test="${param.fieldName}End != null">
                    <![CDATA[AND ${param.columnName} <= ${r'#{'}${param.fieldName}End}]]>
                </if>
            <#else>
                <if test="${param.fieldName} != null">
                    AND ${param.columnName} = ${r'#{'}${param.fieldName}}
                </if>
            </#if>
        </#list>
    </select>



    <!--  请根据此方法,书写自己的update方法,不可以直接使用-->
    <update id="update${sysFieldTableName}ById">
        UPDATE  `${sysTableName}` SET
        <#list sysParams as param>
            <#if param.columnName != "id">
                `${param.columnName}`=${r'#{'}${param.fieldName}}<#if param_has_next>,</#if>
            </#if>
        </#list>
        where
        id =${r'#{id}'}
    </update>



    <!--  请根据此方法,书写自己的update方法,不可以直接使用-->
    <insert id="save${sysFieldTableName}">
        INSERT INTO `${sysTableName}` (
        <#list sysParams as param>
            <#if param.columnName != "id">
                `${param.columnName}`<#if param_has_next>,</#if>
            </#if>
        </#list>
        ) values (
        <#list sysParams as param>
            <#if param.columnName != "id">
                ${r'#{'}${param.fieldName}}<#if param_has_next>,</#if>
            </#if>
        </#list>
        )
    </insert>

    <delete id="delete${sysFieldTableName}ById">
        DELETE from `${sysTableName}` where id = ${r'#{id}'}}
    </delete>

</mapper>
