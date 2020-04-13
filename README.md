# spring-boot-enterprise
## 目标开箱即用

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|接口返回的数据结构一致性和统一序列化|接口返回的数据结构一致性和统一序列化|100%||


##使用说明
### 接口返回的数据结构一致性和统一序列化
1.数据使用`ResultCode`封装,状态码通过`ResultVo`来定义,也可以使用默认的`CUSTOM_FAIL`附加自定义信息
响应数据使用fastjson序列化 `MyMvcConfig` --> `extendMessageConverters`,


