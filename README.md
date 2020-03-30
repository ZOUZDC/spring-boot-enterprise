# spring-boot-enterprise

|序号|功能|需求|完成度|实现的功能|
|---|---|---|---|---|
|1|全局异常处理|各种异常处理|100%|全局异常|



##一,拦截controller中的错误---- zdc.enterprise.constants.AllExceptionHandler


### 1.@RestControllerAdvice与@ControllerAdvice对比

#### 1)都可以指向控制器的一个子集
```java
// 指向所有带有注解@RestController的控制器
@ControllerAdvice(annotations = RestController.class)
public class AnnotationAdvice {}

// 指向所有指定包中的控制器
@ControllerAdvice("org.example.controllers")
public class BasePackageAdvice {}

// 指向所有带有指定签名的控制器
@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})
public class AssignableTypesAdvice {}
```

#### 2)区别
@RestControllerAdvice是@Controller和ResponseBody的集合

#### javadoc

@ControllerAdvice
```java
/**
 * Specialization of {@link Component @Component} for classes that declare
 * {@link ExceptionHandler @ExceptionHandler}, {@link InitBinder @InitBinder}, or
 * {@link ModelAttribute @ModelAttribute} methods to be shared across
 * multiple {@code @Controller} classes.
 *
 * <p>Classes annotated with {@code @ControllerAdvice} can be declared explicitly
 * as Spring beans or auto-detected via classpath scanning. All such beans are
 * sorted based on {@link org.springframework.core.Ordered Ordered} semantics or
 * {@link org.springframework.core.annotation.Order @Order} /
 * {@link javax.annotation.Priority @Priority} declarations, with {@code Ordered}
 * semantics taking precedence over {@code @Order} / {@code @Priority} declarations.
 * {@code @ControllerAdvice} beans are then applied in that order at runtime.
 * Note, however, that {@code @ControllerAdvice} beans that implement
 * {@link org.springframework.core.PriorityOrdered PriorityOrdered} are <em>not</em>
 * given priority over {@code @ControllerAdvice} beans that implement {@code Ordered}.
 * In addition, {@code Ordered} is not honored for scoped {@code @ControllerAdvice}
 * beans &mdash; for example if such a bean has been configured as a request-scoped
 * or session-scoped bean.  For handling exceptions, an {@code @ExceptionHandler}
 * will be picked on the first advice with a matching exception handler method. For
 * model attributes and data binding initialization, {@code @ModelAttribute} and
 * {@code @InitBinder} methods will follow {@code @ControllerAdvice} order.
 *
 * <p>Note: For {@code @ExceptionHandler} methods, a root exception match will be
 * preferred to just matching a cause of the current exception, among the handler
 * methods of a particular advice bean. However, a cause match on a higher-priority
 * advice will still be preferred over any match (whether root or cause level)
 * on a lower-priority advice bean. As a consequence, please declare your primary
 * root exception mappings on a prioritized advice bean with a corresponding order.
 *
 * <p>By default, the methods in an {@code @ControllerAdvice} apply globally to
 * all controllers. Use selectors such as {@link #annotations},
 * {@link #basePackageClasses}, and {@link #basePackages} (or its alias
 * {@link #value}) to define a more narrow subset of targeted controllers.
 * If multiple selectors are declared, boolean {@code OR} logic is applied, meaning
 * selected controllers should match at least one selector. Note that selector checks
 * are performed at runtime, so adding many selectors may negatively impact
 * performance and add complexity.
````


@RestController
```java
/**
 * A convenience annotation that is itself annotated with
 * {@link ControllerAdvice @ControllerAdvice}
 * and {@link ResponseBody @ResponseBody}.
 *
 * <p>Types that carry this annotation are treated as controller advice where
 * {@link ExceptionHandler @ExceptionHandler} methods assume
 * {@link ResponseBody @ResponseBody} semantics by default.
 *
 * <p><b>NOTE:</b> {@code @RestControllerAdvice} is processed if an appropriate
 * {@code HandlerMapping}-{@code HandlerAdapter} pair is configured such as the
 * {@code RequestMappingHandlerMapping}-{@code RequestMappingHandlerAdapter} pair
 * which are the default in the MVC Java config and the MVC namespace.
 *
```

### 2.ExceptionHandler
存在多个ExceptionHandler时,有优先级的,越细化的判断优先级越高


##二,拦截404或者服务器错误等未进入controller的错误 -- zdc.enterprise.controller.OtherExceptionHandler

## 三,filter中的错误----zdc.enterprise.constants.MyFilter
1),通过转发或者重定向到/error接口(未测试)
2),抛异常然后通过OtherExceptionHandler返回数据
3),通过直接向response中写数据返回,此处需要在之前做跨域

