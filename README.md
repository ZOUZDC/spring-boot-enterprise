# spring-boot-enterprise

|序号|功能|需求|完成度|关联功能|
|---|---|---|---|---|
|1|集成Mybatils和MybatisPlus|集成Mybatils和事务测试|90%||

[TOC]
## 一.连接mysql配置连接池
#### 1.连接mysql
```
spring: 
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver #mysql 8.0
    url: jdbc:mysql:///zdc_test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE&serverTimezone=GMT%2B8&useSSL=false&allowPublicKeyRetrieval=true
    username: root
    password: 123123
```
使用了profile 在application.yml中查看对应的具体实现

#### 2.配置hikari连接池

默认hikariCP 无需改动

```
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource #当前使用的数据源 Hikari ,当为Hikari的时候可以不写
    hikari:
      minimum-idle: 1 #	池中维护的最小空闲连接数 默认10 根据实际情况来
      maximum-pool-size: 10 # 池中最大连接数    根据实际情况来
      auto-commit: true  # 自动提交从池中返回的连接
      idle-timeout: 600000 # 一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟
      max-lifetime: 1800000 # 一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，建议设置比数据库超时时长少30秒，参考MySQL
      connection-timeout: 30000   # 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
      connection-test-query: select 1
      read-only: false      # 是否是只读
```

#### 2.配置Druid连接池及监控
pom.xml中添加
```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.14</version>
</dependency>
```
排除`spring-boot-starter-jdbc`的HikariCP
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
    <exclusions>
        <!--HikariCP 连接池-->
        <!--<exclusion>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
        </exclusion>-->
    </exclusions>
</dependency>

```

```
   spring:
     datasource:
       type: com.alibaba.druid.pool.DruidDataSource #当前使用的数据源 druid
       druid:
         initial-size: 2 #初始化连接大小 根据实际情况来
         min-idle: 1 #	池中维护的最小空闲连接数 默认10 根据实际情况来
         max-active: 10 # 池中最大连接数    根据实际情况来
         default-auto-commit: true # 自动提交从池中返回的连接
         test-on-borrow: true #申请连接时执行validationQuery检测连接是否有效。
         max-evictable-idle-time-millis: 1800000 # 最大生存的时间毫秒
         min-evictable-idle-time-millis: 300000  #最小生存的时间毫秒
         validation-query: select 1
         default-read-only: false  # 是否只读
         aop-patterns: zdc.enterprise.service.impl.*.* # 监控切入点
         filter:               #过滤器配置
           stat:
             enabled: true
             merge-sql: true
             slow-sql-millis: 1
         stat-view-servlet:    # 监听配置
           enabled: true              # 是否启用监控
           url-pattern: /druid/*      #访问路径
           reset-enable: false
           login-username: 123123     #访问账号
           login-password: 123123

```

## 二.Mybatis整合代码的位置
#### 1.pom.xml
```  
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.1</version>
</dependency>
```

#### 2.启动类
```
@MapperScan("zdc.enterprise.mapper") //mapper的接口包路径
```
#### 3.application.yml配置类
```
mybatis:
  mapper-locations: "classpath:/mappers/*Mapper.xml"  //mapper.xml位置
  type-aliases-package: zdc.enterprise.entity         
  configuration:
    use-generated-keys: true                          //使用数据库自增主键
```


## 三.事务代码位置
### 1.注解式事务
#### 1).启动类
添加
```
@EnableTransactionManagement   //开启事务
```
#### 2).Service实现类

添加符合场景的 `@Transactional`,
可以直接在class上面添加@Transactional,然后在非事务方法上添加`@Transactional(propagation=Propagation.SUPPORTS)`
也可以只在事务方法上添加`@Transactional(propagation=Propagation.REQUIRED)` 不过容易忘

### 2.编程式事务
#### 1).pom.xml
添加
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```
#### 2).启动类
添加
```
@EnableTransactionManagement   //开启事务
```
#### 3).事务配置文件 
添加事务配置类---> `MyTransactionConfig`

### 3.事务测试
当事务设置为REQUIRED时,
try-catch,会导致事务失效,不能回滚
try-catch-throw,事务不会失效,可以回滚.
简单测试,当项目中使用的时候请,自己做测试



## 四.@Transactional 注解的属性介绍

#### value 和 transactionManager
当配置了多个事务管理器时，可以使用该属性指定选择哪个事务管理器.

#### propagation 
事务的传播行为，默认值为 Propagation.REQUIRED。

可选的值有：

Propagation.REQUIRED
如果当前存在事务，则加入该事务，如果当前不存在事务，则创建一个新的事务。

Propagation.SUPPORTS
如果当前存在事务，则加入该事务；如果当前不存在事务，则以非事务的方式继续运行。

Propagation.MANDATORY
如果当前存在事务，则加入该事务；如果当前不存在事务，则抛出异常。

Propagation.REQUIRES_NEW
重新创建一个新的事务，如果当前存在事务，暂停当前的事务。

Propagation.NOT_SUPPORTED
以非事务的方式运行，如果当前存在事务，暂停当前的事务。

Propagation.NEVER
以非事务的方式运行，如果当前存在事务，则抛出异常。

Propagation.NESTED
和 Propagation.REQUIRED 效果一样。

#### isolation  

事务的隔离级别，默认值为 Isolation.DEFAULT。

可选的值有：

Isolation.DEFAULT

使用底层数据库默认的隔离级别。

Isolation.READ_UNCOMMITTED
Isolation.READ_COMMITTED
Isolation.REPEATABLE_READ
Isolation.SERIALIZABLE

|事务隔离级别|脏读|不可重复读|幻读|
|---|---|---|---|
|读未提交（read-uncommitted）	|是	|是	|是|
|不可重复读（read-committed）	|否	|是	|是|
|可重复读（repeatable-read）	|否	|否	|是|
|串行化（serializable）	|否	|否	|否|

####  timeout
事务的超时时间，默认值为-1。如果超过该时间限制但事务还没有完成，则自动回滚事务。

####  readOnly 
指定事务是否为只读事务，默认值为 false；为了忽略那些不需要事务的方法，比如读取数据，可以设置 read-only 为 true。

#### rollbackFor 
用于指定能够触发事务回滚的异常类型，可以指定多个异常类型。

#### noRollbackFor
抛出指定的异常类型，不回滚事务，也可以指定多个异常类型。

