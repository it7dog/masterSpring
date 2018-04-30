IOC: Inverse OF Control 创建对象的方式 反转,使用spring之后，对象的创建以及依赖关系由spring完成创建及注入。   
DI: Dependency Injection 依赖注入 实现IOC思想需要DI做支持。  
```
注入方式：  
    set方法注入  
    构造方法注入  
    字段注入  
```
```
注入类型
  值类型注入  8大基本类型
  引用类型注入 将依赖对象注入
```
ApplicationContext 每次容器启动时就会创建容器中配置的所有对象，并提供更多功能。    
在web开发中，使用applicationContext。在资源匮乏的环境可以使用BeanFactory。  
从类路径下加载配置文件：
 ClassPathXmlApplicationContext  
 从硬盘绝对路径下加载配置文件：FileSystemXmlApplicationContext  
 
 Bean元素：使用该元素描述需要spring容器管理的对象。
   name属性：给被管理的对象起个名字，获得对象时根据该名称获得对象。可以重复，可以使用特殊字符。
   class属性：被管理对象的完成类名。
   id属性：与name属性一摸一样，名称不可重复，不可使用特殊字符。
   
 尽量使用 name属性
 
 spring创建对象的方式：  
  1、空参构造方式
  2、静态工厂（了解）
  3、实例工厂（了解）
  
scope属性  
 singleton(默认值)，单利对象，在spring容器中只会存在一个实例  
 prototype多例，被标示对象，每次再获得才会创建，每次创建都是新的对象。整合struts2时，ActionBean必须配置为多例。    
 request（web环境下，对象与request生命周期一致）  
 session（web环境下，对象与session生命周期一致）  
