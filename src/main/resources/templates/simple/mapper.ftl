<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPath?lower_case}.${sysFieldTableName}">

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
                    AND ${param.columnName} like concat ('%', '${r'#{'}${param.fieldName}}' , '%' )
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



    <!-- 查询已存在 xyz是要验证的字段名 -->
    <select id="exist" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List" />
        FROM ${sysTableName}
        WHERE 1=1
        AND XYZ = ${r'#{'}xyz}
        <#list sysParams as param>
            <#if param.isKey == "true">
                <if test="${param.fieldName} != null">
                    AND ${param.columnName} != ${r'#{'}${param.fieldName}}
                </if>
            </#if>
        </#list>
    </select>



</mapper>
