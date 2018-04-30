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
 
 生命周期属性  
  配置一个方法做为生命周期初始化方法。spring会在对象创建之后立即调用。  
  配置一个方法作为生命周期的销毁方法。spring容器会在关闭并销毁所有容器中的对象之前调用。 
  
  
spring属性注入  

 set方法注入（值类型注入 引用类型注入）  
 
 构造函数注入  name属性：构造函数的参数名，index属性，构造函数的参数索引，type属性，构造函数的参数类型   
  
 p名称空间注入 1.导入p命名空间  xmlns:p="http://www.springframework.org/schema/p   
 2.使用p:属性完成注入 值类型：  
 p:属性名 = "值"  
 对象类型：p:属性名-ref="bean名称"  
 
 spel注入 spring表达式语言  
 
 复杂类型注入  
 ````
 <bean id="collectionBean" class="cn.itcast.spring.demo5.CollectionBean">  
 <!-- 数组类型的属性 -->   
 <property name="arrs">  
 <list> 
   <value>会希</value> 
   <value>冠希</value> 
   <value>天一</value>
 </list>
 </property>
 
 <!-- 注入 List 集合的数据 --> 
 <property name="list">
 <list> 
    <value>芙蓉</value> 
    <value>如花</value> 
    <value>凤姐</value>
 </list>
 </property>
 <!-- 注入 Map 集合 --> 
 <property name="map">
    <map>
        <entry key="aaa" value="111"/> 
        <entry key="bbb" value="222"/> 
        <entry key="ccc" value="333"/>
    </map>
 </property>
 <!-- Properties 的注入 --> 
 <property name="properties">
    <props>
        <prop key="username">root</prop> 
        <prop key="password">123</prop>
    </props>
 </property>
 </bean>
 ````
 