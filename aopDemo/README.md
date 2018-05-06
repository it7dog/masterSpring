#### IOC及DI复习
```
 <!-- Spring 的注解开发:组件扫描(类上注解: 可以直接使用属性注入的注解) --> 
 <context:component-scan base-package="com.itheima.spring.demo1"/>
```
@Component:组件.(作用在类上)  
pring 中提供@Component 的三个衍生注解:(功能目前来讲是一致的)  
* @Controller
* @Service
* @Repository  
:WEB 层 :业务层 :持久层
这三个注解是为了让标注类本身的用途清晰，Spring 在后续版本会对其增强.  
@Value :用于注入普通类型.  
@Autowired :自动装配: * 默认按类型进行装配. * 按名称注入:
* @Qualifier:强制使用名称注入. @Resource 相当于:  
* @Autowired 和@Qualifier 一起使用.  
#### AOP 思想
> 横向重复，纵向抽取  

  在软件业，AOP为Aspect Oriented Programming的缩写，意为：面向切面编程，通过预编译方式和
  运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，
  也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，
  从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。 
    
 对程序进行增强:不修改源码的情况下.
* AOP 可以进行权限校验,日志记录,性能监控,事务控制。

##### 底层实现   
代理机制：
 * Spring 的 AOP 的底层用到两种代理机制:  
 1.JDK 的动态代理 :针对实现了接口的类产生代理.  
 2.Cglib 的动态代理 :针对没有实现接口的类产生代理. 应用的是底层的字节码增强的技术 生成当前类
 的子类对象.
 
##### 名词解释
* joinpoint(连接点)目标对象中，所有可以增强的方法。  

* pointcut(切入点):目标对象中已经增强的方法。  

* Advice(通知/增强):增强的代码  

* Target(目标对象): 被代理对象

* Weaving(织入):将通知应用到切入点的过程  

* Proxy(代理):将通知织入到目标对象之后，形成的代理对象。  

* Aspect(切面)：切入点+通知  

##### 配置切入点  

```
public void cn.itcast.service.UserServiceImpl.save()  
void cn.itcast.service.UserServiceImpl.save()  
* cn.itcast.service.UserServiceImpl.save() 
* cn.itcast.service.UserServiceImpl.*() 
* cn.itcast.service.*ServiceImpl.*(..) 
* cn.itcast.service..*ServiceImpl.*(..) 
```

##### AOP 面向切面编程思想

横向重复代码，纵向抽取。  

###### 动态代理
1.通过动态代理可以体现aop思想  
2.对象目标对象的方法进行增强。  

###### spring aop开发  
spring封装了动态代理代码，我们不需要手写动态代理代码。  
还封装了cglib代理。  

##### 注解配置


