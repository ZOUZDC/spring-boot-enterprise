# spring-boot-enterprise

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|统一数据返回|接口返回的数据结构一致性和统一序列化|100%||


##使用说明
>数据使用`ResultCode`封装,状态码通过`ResultVo`来定义,也可以使用默认的`CUSTOM_FAIL`附加自定义信息

响应数据使用fastjson序列化 `MyMvcConfig` --> `extendMessageConverters`,


全局数据统一返回则需要配合全局异常来使用

拓展阅读 ResponseBodyAdvice

