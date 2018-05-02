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