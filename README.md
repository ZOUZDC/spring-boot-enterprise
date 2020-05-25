# spring-boot-enterprise

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|入参验证|入参验证及其异常捕获|100%|完成|
|2|参数处理|过滤修改添加字段|100%|完成|
|3|入参时间类型的处理|date,localDate等类型的处理|100%|完成|



## 一.入参验证及其异常捕获

### 1) 异常处理请看902全局异常处理` 
在这里`AllExceptionHandler` 捕获并重写了`BindException`异常.
不要轻易继承`ResponseEntityExceptionHandler`该类

### 2) 简单的参数类型验证请看 StudentSimpleDto 
在这里建议只做数据类型方面的判断,如果存在非空 非null之类的判断 则该类可能不会在增删改查等方法中通用.

### 3) 数据类型验证和数据是否为空验证请看 StudentGroupDto
比2)增加了非空非null之类的判断, 通过分组之后 ,增删改查之类的方法都可以调用此类,比较通用

### 4) 在controller中验证的时候推荐使用`@Validated`
### 5) 请不要直接在controller的形参中使用校验注解
在controller中直接使用校验注解是无效的,需要改代码


## 二.参数处理
### 1)重写ParameterRequestWrapper中的方法
### 2)在filter中调用该实现
### 3)在读取数据的时候去掉前后的空格
### 4)添加指定数据或者删除指定数据
### 5)写回到map中


## 三.入参时间类型的处理
实现了 LocalDate,LocalDateTime,LocalTime 正确格式的入参数据的转换
实现了 Date类型 `yyyy-MM-dd` ,`yyyy-MM-dd HH:mm:ss` ,`second` ,`millsecond` 类型 正确格式的入参的数据转换 ,如有需要自己另行添加 

## 四.validationde的group类 --- zdc.enterprise.constants.Dto
需要继承默认的Default ,否则只会验证包含对应groups的参数