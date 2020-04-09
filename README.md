# spring-boot-enterprise

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|简单代码生成|根据项目生成指定的代码|100%||





##使用说明
根据项目代码生成基础代码.
写这个的目的就是单纯的根据表生成文件

`zdc.enterprise.AutoCode`为入口文件
使用的流程是将一个写好的代码复制到模版中,将一些数据库参量直接替换

>建议:能在模版上写死的就不要配置变量,毕竟要让工具适应项目
参量添加前后缀或者在模版中添加常规信息,建议直接写在模版中


#### 现在模版中提供的默认变量

例子: 

数据库表名:zdc_orders <br/>
文件前缀: hello <br/>
文件后缀: bye <br/>
 
|字段|解释|例子|
|---|---|---|
|sysFieldTableNameSuffix|实体类表名加前后缀|HelloZdcOrdersBye|
|sysFieldTableName|实体类表名不加前后缀|ZdcOrders|
|sysTableName|数据库表名|zdc_order|
|sysParams|列信息|字段列表|

#### sysParams列表中的字段

例子: 

数据库字段名:orders_name <br/>
字段类型: varchar <br/>


|字段|解释|例子|
|---|---|---|
|columnName|数据库列名|order_name|
|dataType|数据库字段类型|varchar|
|isKey|是否是主键|false|
|notNull|是否非空|false|
|comment|备注|订单名称|
|fieldName|实体属性名|orderName|
|fieldType|实体属性类型|String|
|fieldNote|字段说明|(同备注)订单名称|

### 项目中部分代码(MysqlUtil,autocode)来源于
https://github.com/tenny-peng/autocode
autocode中的模版只是一个例子,不可以直接使用