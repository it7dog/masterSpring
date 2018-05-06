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

##### 增强类型  

* 前置增强 在目标方法执行前实施增强  
* 后置增强  在目标方法执行后实施增强
* 环绕增强 在目标方法执行前后实施增强
* 异常抛出增强 在目标方法抛出异常后实施增强
* 引介增强 在目标类中添加一些新的方法和属性。  

##### execution()  
 execution()是最常用的切点函数，其语法如下： 
 execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>) <异常模式>?)  
 1.通过方法签名定义切点  
 execution(public * *(..)):匹配所有目标类的public方法，第一个\*代表返回值类型；第二个\*代表
 方法名；而..代表任意入参的方法。    
 execution(* *To(..)):匹配目标类所有以To为后缀的方法，第一个\*代表返回类型；而\*To代表任意以To
 为后缀的方法。  
 2.通过类定义切点  
 execution(\* com.smart.Waiter.\*(..)):匹配Waiter接口的所有方法，第一个\*代表返回任意类型；
 com.smart.Waiter.\*代表Waiter接口中的所有方法。  
 execution(\* com.smart.Waiter+.\*(..)):匹配Waiter接口及其所有实现类的方法。  
 3.通过类包定义切点  
 在类名模式串中,".\*"表示包下所有的类，而"..\*"表示包、子孙包下的所有类。  
 execution(\* com.smart.\*(..))：匹配com.smart包下所有类的所有方法。  
 execution(\* com.smart..\*(..)):匹配com.smart包、子孙包下所有类的的所有方法。  
 execution(\* com..\*.\*Dao.find\*(..)):匹配包名前缀为com的任何包下类名后缀为Dao的方法，方法名
 必须以find为前缀。  
 4.通过方法入参定义切点  
 可以使用\*和..通配符。其中\*表示任意类型的参数;而..表示任意类型的参数且参数个数不限。  
 execution(\* joke(String,int))：匹配joke(String,int)方法，且joke方法的第一个入参是String,
 第二个入参是int。  
 execution(\* joke(String,\*))：匹配目标类中的joke()方法，该方法第一个参数类型为String，第二个
 可以是任意类型。  
 execution(\*joke(String,..)):匹配目标类中的joke()方法，该方法第一个入参为String，后面可以
 有任意个入参且入参类型不限。  
 executuon(\*joke(Object+)):匹配目标类中的joke()方法，方法拥有一个入参，且入参是Object类型或者
 该类的子类。  
 
 
 
