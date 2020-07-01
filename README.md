# spring-boot-enterprise

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|日志|日志的分割,日志的级别|80%||



##使用说明
不区分多环境的情况下使用logback.xml或者logback-spring.xml 均可
当需要根据多环境的profile来分别配置logback的参数的话需要使用logback-spring.xml

#### 
根据application.yml中的位置,来指定生成日志文件位置
```
logging:
  file:
    path: ./logs/
```