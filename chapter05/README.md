##### Bean的基本配置

* Spring支持两种依赖注入方式，分别为属性注入和构造函数注入。除此之外,spring还支持工厂方法注入方式。
 

###### 属性注入 
* 属性注入是指通过setXxx()方法注入Bean的属性值或依赖对象。在实际应用中最长采用的注入方式。
###### 构造函数注入
* 构造函数注入是除属性注入外的另一种常用的注入方式，它能保证一些必要的属性在Bean实例化时就得到设置，确保Bean在实例化后就可以使用。  
1.按类型匹配入参  
    ```
    public Class car{
        public Car(String brand,double price){
          this.brand = brand;
          this.price = price;
       }
   }
   ```
注入方式：
```
<bean id="car1" class="com.smart.ditye.Car">
    <constructor-arg type="java.lang.String">
        <value>红旗CA72</value>
    </constructor-arg>
    <constructor-arg type="double">
      <value>20000</value>
   </constructor-arg>
</bean>
```
在<constructor-arg>的元素中有一个type属性，它为Spring提供了判断配置项和构造函数入参对应关系的"信息"。  
2.按索引匹配入参
```
public Car(String brand,String corp,double price){
    this.brand = brand;
    this.corp=corp;
    this.price=price;
}
```
通过入参位置索引确定对应关系
    
    <bean id="car2" class="com.smart.ditype.Car">
        <constructor-arg index="0" value="红旗CA72"/>
        <constructor-arg index="1" value="中国一汽"/>
        <constructor-arg index="2" value="20000"/>
    </bean>
    
3.联合使用类型和索引匹配入参  
```
    public Car(String brand,String corp,double price){
        this.brand = brand;
        this.corp=corp;
        this.price=price;
    }
```
    public Car(String brand,String corp,int maxSpeed){
        this.brand = brand;
        this.corp=corp;
        this.maxSpeed=maxSpeed;
    }
通过入参类型和位置索引确定对应关系
```
<bean id="car3" class="com.smart.ditype.Car">
    <constructor-arg index="0" type="java.lang.String">
        <value>红旗CA72</value>
    </constructor-arg>
    <constructor-arg index="1" type="java.lang.String">
       <value>中国一汽</value>
    </constructor-arg>
    <constructor-arg index="2" type="int">
      <value>20000</value>
    </constructor-arg>
</bean>
```
4.通过自身类型反射匹配入参
```
public Boss(String name,Car car, Office office){
    this.name = name;
    this.car = car;
    this.office=office;
}
```
由于car、office和name入参的类型都是可辨别的，所以无须在构造函数注入的配置时指定<constructor-arg>的类型和索引，因此我们可以采用如下简易的配置方式：
```
<bean id="boss" class="com.smart.ditype.Boss">
    <constructor-arg>
        <value>John</value>
    </constructor-arg>
    <constructor-arg>
     <ref bean="car" />
    </constructor-arg>
    <constructor-arg>
      <ref bean="office" />
    </constructor-arg>
</bean>
<bean id="car" class="com.smart.ditype.Car"/>
<bean id="office" class="com.smart.ditype.Office"/>
```
5.工厂方法注入
* 非静态工厂方法
```
public class CarFactory{
    public Car createHongQiCar(){
        Car car = new Car();
        car.setBrand("红旗CA72");
        return car;
    }
}
```
通过工厂类注入bean
```
<!--工厂类Bean -->
<bean id="carFactory" class="com.smart.ditye.CarFactory" />
<!-- factory-bean指定1处的工厂类Bean;factory-method指定工厂类Bean创建Bean的工厂方法-->
<bean id="car5" factory-bean="carFactory" factory-method="createHongQiCar">
```  
* 静态工厂方法
>     
    public class CarFactory{
      public static Car createHongQiCar(){
       ...
      }
    }
当使用静态工厂类型的方法后，用户就无须在配置文件定义工厂类的Bean，只需按以下方式进行配置即可：  
>   
    <bean id="car6" class="com.smart.ditype.CarFactory" factory-method="createHongQiCar" />
直接在<bean>中通过class属性指定工厂类，然后再通过factory-method指定对应的工厂方法。

###### 注入参数详解
* 字面值一般指可用字符串表示的值，这些值可以通过<value>元素标签进行注入。在默认情况下，基本数据类型及其封装类、String等类型都可以采用字面值注入的方式。
>

     <bean id="car" class="com.smart.attr.Car">
      <property name="maxSpeed">
         <value>200</value>
      </property>
      <property name="brand">
         <value><![CDATA[红旗&CA72]]></value>
      </property>
     </bean>  
     
 \<![CDATA[]]的作用是让XML解析器将标签中的字符串当做普通的文本对待。
 
 使用转义可以表示为
 >
      <property name="brand">
        <value>红旗&amp;CA72</value>
      <property>
* 引用其他Bean
```
public class Boss {
    private Car car;
    public void setCar(Car car){
        this.car = car;
    }
}
```
boss的bean通过<ref>元素引用car Bean,建立起boss对car的依赖。
```
<bean id="car" class="com.smart.attr.Car" />
<bean id="boss" class="com.smart.attr.Boss">
 <!--引用上面定义的car Bean-->
  <ref bean="car"></ref>
</bean>
```
* 内部Bean
```
<bean id="boss" class="com.smart.attr.Boss">
    <property name="car">
        <bean class="com.smart.attr.Car">
            <property name="maxSpeed" value="200" />
            <property name="price" value="2000.00" />
        </bean>
    </property>
</bean>
```
* null值
```
<bean id="car" class="com.smart.attr.Car">
    <property name="brand"><null/></property>
</bean>
```
* 级联属性
```
<bean id="boss3" class="com.smart.attr.Boss">
    <property name="car.brand" value="吉利CT50" />
</bean>
```
按照上面的配置，Spring将调用Boss.getCar().setBean("吉利CT50")进行属性的注入操作。这时必须对Boss类进行改造，为car属性声明一个初始化对象。
```
public class Boss{
 private Car car = new Car();
 public Car getCar(){
       return car;
  }
  public void setCar(Car car){
   this.car=car;
  }
 }
}
```
* 集合类型属性
> List：
```
public class Boss{
    private List favorites = new ArrayList();
    public List getFavorites(){
        return List;
    }
    public void setFavorites(List favorites){
        this.favorites=favorites;
    }
}
```
对应的Spring中的属性配置片段
```
<bean id="boos1" class="com.smart.attr.Boss">
    <property name="favorites">
        <list>
            <value>看报</value>
            <value>赛车</value>
            <value>高尔夫</value>
        </list>
    </property>
</bean>
```
> Set:
```
<bean id="boos1" class="com.smart.attr.Boss">
    <property name="favorites">
        <set>
            <value>看报</value>
            <value>赛车</value>
            <value>高尔夫</value>
        </set>
    </property>
</bean>
```
> Map:  
>
 下面为Boss添加一个Map类型的jobs属性
```
public class Boss{
    private Map jobs = new HashMap();
    public Map getJobs(){
        return jobs;
    }
    public void setJobs(Map jobs){
        this.jobs=jobs;
    }
}
```
在配置文件中可以通过以下方式为jobs属性提供配置值：
```
<bean id="boss1" class="com.smart.attr.Boss">
    <property name="jobs">
        <map>
         <entry>
            <key><value>AM</value></key>
            <value>会见客户</value>
         </entry>
         <entry>
           <key><value>PM</value></key>
           <value>公司内部会议</value>
         </entry>
        </map>
    </property>
</bean>
```
> Properties
>
properties类型是Map类型的特例。Map元素的键和值可以是任何类型的对象，而Properties属性的键和值只能是字符串。
```
public class Boss{
    private Properties mails = new Properties();
    public Properties getMails(){
     return mails;
    }
    public void setMails(Properties mails){
     this.mails = mails;
    }
}
```
配置：
```
<bean id="boss1" class="com.smart.attr.Boss">
    <property name="mails">
        <props>
         <prop key="jobMail">john-office@smart.com</prop>
         <prop key="lifeMail">john-life@smart.com</prop>
        </props>
    </property>
</bean>
```
> 强类型集合
>
```
public class Boss {
    private Map<String,Integer> jobTime = new HashMap<String,Integer>();
    public Map<String,Integer> getJobTime(){
        return jobTime();
    }
    public void setJobTime(Map<String,Integer> jobTime){
        this.jobTime=jobTime;
    }
}
```
```
<bean id="boss1" class="com.smart.attr.Boss">
 <property name="jobTime">
  <map>
   <entry>
    <key><value>会见客户</value></key>
    <value>124</value>
   </entry>
  </map>
 </property>
</bean>
```
> 集合合并
>
Spring支持集合合并的功能，允许子<bean>继承父<bean>的同名属性集合元素，并将子<bean>中配置的集合属性和父<bean>中配置的同名属性值合并起来
作为最终Bean的属性值。
```
<bean id="parentBoss" abstract="true" class="com.smart.attr.Boss">
    <property name="favorites">
        <set>
         <value>看报</value>
         <value>赛车</value>
         <value>高尔夫</value>
        </set>
    </property>
</bean>
```
```
<bean id="childBoss" parent="parentBoss">
    <property name="favorites">
        <set merge="true">
            <value>爬山</value>
            <value>游泳</value>
        </set>
    </property>
</bean>
```
###### 简化配置方式
为了简化xml文件的配置，越来越多的XMl文件采用属性而非子元素配置信息。Spring从2.5版本开始引入一个新的p命名空间。可以通过<bean>元素属性的方式配置bean的属性。
```
<?xml version="1.0" encoding="UTF-8">
<beans xmlns="" >
    <bean id="car" class="com.smart.ditype.Car">
        <property name="brand" value="红旗&amp;CA72" />
        <property name="maxSpeed" vallue="2000" />
        <property name="price" value="200000" />
    </bean>
</beans
```
使用p命名空间后
```
<?xml version="1.0" encoding="UTF-8">
<beans xmlns=""
    xmlns="http://www.springframework.com/schema/p" // 生命p命名空间
>
    <bean id="car" class="com.smart.ditype.Car"
        p:brand="红旗&amp;CA72"
        p:maxSpeed="200"
        p:price="200000.00"
    />
    <bean id="boss" class="coom.smart.ditype.Boss"
     p:car-ref="car" />
</beans>
```
未采用p命名空间前，<bean>使用<property>子元素配置Bean的属性，采用p命名空间后，采用<bean>的元素属性配置Bean的属性。
>    
    对于字面属性，其格式为：
    p:<属性名>="xxx"
    对于引用对象的属性，其格式为：
    p:<属性名>-ref="xxx"

###### \<bean>之间的关系
* 继承
```
<bean id=abstractcar" class="com.smart.tagdepend.Car"
  p:brand="红旗CA72" p:price="2000.00" p:color="黑色" abstract="true" />

<bean id="car3" p:color="红色" parent="abstractcar" />
<bean id="car4" p:color="白色" parent="abstractcar" />
```
* 依赖  

一般情况下，可以使用<ref>元素标签建立对其他Bean的依赖关系，Spring负责管理这些Bean的关系。当实例化一个Bean时，Spring保证该Bean所依赖的其他Bean已经初始化。  

Spring允许用户通过depends-on属性显示指定Bean前置依赖的Bean，前置依赖的Bean会在本Bean实例化之前创建好。
```
<bean id="manager" class="com.smart.tagdepend.CacheManager" depends-on="sysInit" />
<bean id="sysInit" class="com.smart.tagdepend.SysInit" />
```
* 引用
```
<bean id="car" class="com.smart.tagdepend.Car" />
<bean id="boss" class="com.smart.tagdepend.Boss" p:car="car" scope="prototype" />
```

Spring提供了一个<idref>元素标签，可以通过<idref>引用另一个<bean>的名字。在容器启动时，Spring负责检查引用关系的正确性，这样就可以提前发现错误.
```
<bean id="car" class="com.smart.tagdepend.Car" />
<bean id="boss" class="com.smart.tagdepend.Boss">
 <property name="carId">
    <idref bean="car" />
 </property>
</bean>
```
###### 整合多个配置文件
```
<import resource="classpath:com/smart/impt/beans1.xml" />
<bean id="boss1" class="com.smart.fb.Boss" p:name="John" p:car-ref="car1" />
<bean id="boss2" class="com.smart.fb.Boss" p:name="John" p:car-ref="car2" />
```
假设已经在beans1.xml中配置了car1和car2的Bean,通过import的resource属性引入beans1.xml，beans2.xml就拥有了完整的配置信息，Spring容器仅需通过beans2.xml
就可以加载所有的配置信息。
##### Bean作用域
1.singleton作用域  
单利模式是重要的设计模式之一。一般无状态或者状态不可变的类适合使用单例模式。在spring中，大部分Bean都能已单例的方式运行，spring的Bean默认作用域为singleton.
```
<bean id="car" class="com.smart.scope.car" scope="singleton" />
<bean id="boss1" class="com.smart.scope.Boss" p:car-ref="car" />
<bean id="boss2" class="com.smart.scope.Boss" p:car-ref="car" />
<bean id="boss3" class="com.smart.scope.Boss" p:car-ref="car" />
```
boss1、boss2和boss3的car属性都指向同一个Bean  
2.prototype作用域
```
<bean id="car" class="com.smart.scope.Car" scope="prototpye" />
<bean id="boss1" class="com.smart.scope.Boss" p:car-ref="car" />
<bean id="boss2" class="com.smart.scope.Boss" p:car-ref="car" />
<bean id="boss3" class="com.smart.scope.Boss" p:car-ref="car" />
```
boss1、boss2和boss3所引用的都是一个新的carsih实例。
##### 基于注解的配置
* 使用注解定义Bean  
> 使用注解定义一个DAO的Bean
```
package com.smart.anno;
import org.springframework.stereotype.Component;
@Component("userDao")
public class UserDao{
   ...
}
```
使用@Component注解在UserDao类声明处对类进行标注，它可以被Spring容器识别，Spring容器自动将POJO转换为容器管理的Bean。  
它和XML配置时等效的：
```
<bean id="userDao" class="com.smart.anno.UserDao" />
```
除@Component外，Spring还提供了3个基本和@Component等效的注解，分别用于对DAO、Service及Web层的Controller进行注解。
>@Repository:用于对DAO实现类进行标注；  
>@Service:用于对Service实现类进行标注。  
>@Controller:用于对Controller实现类进行标注。  

之所以要在@Component之外提供这3个特殊的注解，是为了让标注类本身的用途清晰化，完全可以用@Component替代这3个特殊的注解。（不推荐）
* 扫描注解定义的Bean

Spring提供了一个context命名空间，她提供了通过扫描类包以应用注解定义Bean的方式。
```
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-4.0.xsd">

  <!-- 扫描类包以应用注解定义的Bean-->
  <context:component-scan base-package="com.smart.anno" />
</beans>
```
通过context命名空间的component-scan的base-package属性指定一个需要扫描的基类包，Spring容器将会扫描这个基类包里的所有类，并从类的注解信息中获取Bean的定义信息。

如果仅希望扫描特定的类而非基包下所有的类，那么可以使用resource-pattern属性过滤出特定的类，如下：  
```
<context:component-scan base-package="com.smart" resource-pattern="anno/*.class" />
```
这里讲基包设置为com.smart；默认情况下，resource-pattern属性为"**/*.class"，即基类包里的所有类；将其设置为"anno/*.class"，则Spring仅会扫描基类包里
anno子包中的类。
```
<context:component-scan base-package="com.smart.anno" >
 <context:include-filter type="regex" expression="com\.smart\.anno.*" />
 <context:exlude-filter type="aspectjs" expression="com.smart..*Controller+" />
</context:component-scan>
```

\<context:include-filter>表示要包含的目标类  
\<context:exclude-filter>表示要排除的目标类  
 aspetj的过滤表达能力是最强的，它可以轻松实现其他类型所能表达的过滤规则。
 
 ##### 自动装配Bean
 
 1.使用@Autowired进行自动注入  
 Spring通过@Autowired注解实现Bean的依赖注入。  
 ```
 package com.smart.anno;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class LogonService{
   // 分别注入LogDao及UserDao的Bean
   @Autowired
   private LogDao logDao;
   @Autowired
   private UserDao userDao;
   ....
 }
 ```
 使用@Service将LogonService标注为一个Bean  
 通过@Autowired注入LogDao及UserDao的Bean  
 @Autowired默认按类型（byType）匹配的方式在容器中查找匹配的Bean，当有且仅有一个匹配的Bean时，Spring将其注入@Autowired标注的变量中。  
 2.使用@Autowired的required属性  
 如果容器中没有一个和标注变量匹配的Bean,那么Spring容器启动时将报NoSuchBeanDefinitionException异常。如果希望Spring及时找不到匹配的Bean完成注入也不要
 抛出异常，那么可以使用@Autowired(required=false)进行标注。  
 ```
 @Service
 public class LogonService{
  @Autowired(required=false)
  private LogDao logDao;
  ....
 }
 ```
 3.使用@Qualifier指定注入Bean的名称  
 如果容器中有一个以上匹配的Bean时，则可以通过@Qualifier注解限定Bean的名称
 ```
 package com.smart.anno;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service
 
 @Service
 public class LogonService {
     @Autowired
     private LogDao logDao
 
     @Autowired
     @Qualifier("userDao")
     private UserDao userDao;
 }
 ```
 这时，假设容器有两个类型为UserDao的Bean,一个名为userDao,另一个名为otherUserDao，则会注入名为uesrDao的Bean。
 
 4.对类方法进行标注  
 @Autowired可以对类成员变量及方法的入参进行标注。
 ```
 package com.smart.anno;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service;
 
 @Service
 public class LogService{
     private LogDao logDao;
     private UserDao userDao;
 
     @Autowired
     public void setLogDao(LogDao logDao){
      this.logDao = logDao;
     }
 
     @Autowired
     @Qualifier("userDao")
     public void setUserDao(UserDao userDao){
      this.userDao=userDao;
     }
 ]
 
 @Autowired
 public void init(@Qualifier("userDao")UserDao userDao,LogDao logDao){
   this.userDao=userDao;
   this.logDao=logDao;
 }
 ```
 
 UserDao的入参注入名为userDao的Bean，而LogDao的入参注入LogDao类型的Bean。  
 一般情况下，在spring容器中大部分Bean都是单例的，所以一般无须通过@Repository、@Service等注解的value属性为Bean指定名称，也无须使用@Qualifier
 注解按名称进行注入。
 
 