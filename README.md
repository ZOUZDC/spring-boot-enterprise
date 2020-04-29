# spring-boot-enterprise

|序号|功能|需求|完成度|关联功能|
|---|---|---|---|---|
|1|集成redis|集成redis|80%||

### redis配置
#### pom.xml
``` 
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
#### application.yml
```
spring: 
  redis:
    database: 0
    host: 192.168.253.6
    port: 6379
    password: testdb
```
#### 指定序列化
参看`MyRedisConfig`


#### 简单测试

测试结果在大量数据需要处理的时候建议使用`Pipeline`

#### 建议
建议将redis的常用命令封装为一个脚本,来解耦 ,如果是小型项目 就直接用吧
