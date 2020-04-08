# spring-boot-enterprise

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|入参验证|入参验证及其异常捕获|30%||
|2|入参时间类型的处理|date,localDate等类型的处理|100%|完成|




`异常处理请看902全局异常处理`
在这里`AllExceptionHandler`继承了`ResponseEntityExceptionHandler`重写了



## 入参时间类型的处理
实现了 LocalDate,LocalDateTime,LocalTime 正确格式的入参数据的转换
实现了 Date类型 `yyyy-MM-dd` ,`yyyy-MM-dd HH:mm:ss` ,`second` ,`millsecond` 类型 正确格式的入参的数据转换  