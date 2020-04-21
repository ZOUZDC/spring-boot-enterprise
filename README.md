# spring-boot-enterprise

|序号|功能|需求|完成度|关联功能|
|---|---|---|---|---|
|1|集成Mybatils和MybatisPlus|集成Mybatils和事务测试|90%||


### Mybatis整合代码的位置
#### 1.启动类
添加
```
@MapperScan("zdc.enterprise.mapper") //mapper的接口包路径
```
### application.yml配置类
```
mybatis:
  mapper-locations: "classpath:/mappers/*Mapper.xml"  //mapper.xml位置
  type-aliases-package: zdc.enterprise.entity         
  configuration:
    use-generated-keys: true                          //使用数据库自增主键
```

### 事务代码位置
#### 1.启动类
添加
```
@EnableTransactionManagement   //开启事务
```
### Service实现类

添加符合场景的 `@Transactional`,
可以直接在class上面添加@Transactional,然后在非事务方法上添加`@Transactional(propagation=Propagation.SUPPORTS)`
也可以只在事务方法上添加`@Transactional(propagation=Propagation.REQUIRED)` 不过容易忘


### @Transactional 注解的属性介绍

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

