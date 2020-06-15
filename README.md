# spring-boot-enterprise

|序号|功能|需求|完成度|关联功能|
|---|---|---|---|---|
|1|静态资源处理|静态资源和前端项目文件处理|0%||


很多小型的项目也是前后端分离的项目,如果项目想使用jar包且资源容易发生变动的情况下,就需要为前端项目单独启动一个资源服务.

这个项目就是为让springboot为前端项目提供外部静态资源服务的例子


## 提供前端页面服务关键点
### 1 重定向"/"连接
   springboot默认匹配的是controller中的url,需要将"/"重定向到指定前缀的路径中来保证访问的是静态资源文件,这里设定的是"/webJar",
   也可也强制页面路径直接访问"/webJar"
### 2 将指定静态资源文件的路径 跳过过滤
### 3.在实现WebMvcConfigurer接口的类中声明静态资源路径

````
  registry.addResourceHandler("/webJar/**")
                //内部资源 如果存在相同名字和路径的资源 以上面的为准
                // .addResourceLocations("classpath:/static/")
                //外部资源
                .addResourceLocations("file:"+publicPath+"webJar"+ File.separator);
````
 一个静态资源路径可以对应多个本地资源目录, classpath则是从jar包内部寻找,file是从外部寻找. 写在前面的匹配到了资源优先返回数据
 
 demo资源下载路径 http://localhost/static/SpringBootEnterprise?t=123123  注意缓存

 

