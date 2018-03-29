Bean的基本配置
Spring支持两种依赖注入方式，分别为属性注入和构造函数注入。除此之外,spring还支持工厂方法注入方式。
属性注入
属性注入是指通过setXxx()方法注入Bean的属性值或依赖对象。在实际应用中最长采用的注入方式。
构造函数注入
构造函数注入是除属性注入外的另一种常用的注入方式，它能保证一些必要的属性在Bean实例化时就得到设置，确保Bean在实例化后就可以使用。
1、按类型匹配入参
public Class car{
    public Car(String brand,double price){
        this.brand = brand;
        this.price = price;
    }
}
注入方式：
<bean id="car1" class="com.smart.ditye.Car">
    <constructor-arg type="java.lang.String">
        <value>红旗CA72</value>
    </constructor-arg>
    <constructor-arg type="double">
      <value>20000</value>
   </constructor-arg>
</bean>
在<constructor-arg>的元素中有一个type属性，它为Spring提供了判断配置项和构造函数入参对应关系的"信息"。
2、按索引匹配入参
public Car(String brand,String corp,double price){
    this.brand = brand;
    this.corp=corp;
    this.price=price;
}
通过入参位置索引确定对应关系
<bean id="car2" class="com.smart.ditype.Car">
    <constructor-arg index="0" value="红旗CA72"/>
    <constructor-arg index="1" value="中国一汽"/>
    <constructor-arg index="2" value="20000"/>
</bean>
3、联合使用类型和索引匹配入参
public Car(String brand,String corp,double price){
    this.brand = brand;
    this.corp=corp;
    this.price=price;
}
public Car(String brand,String corp,int maxSpeed){
    this.brand = brand;
    this.corp=corp;
    this.maxSpeed=maxSpeed;
}
通过入参类型和位置索引确定对应关系
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
4、通过自身类型反射匹配入参
public Boss(String name,Car car, Office office){
    this.name = name;
    this.car = car;
    this.office=office;
}
由于car、office和name入参的类型都是可辨别的，所以无须在构造函数注入的配置时指定<constructor-arg>的类型和索引，因此我们可以采用如下简易的配置方式：
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
5、工厂方法注入
1、非静态工厂方法
public class CarFactory{
    public Car createHongQiCar(){
        Car car = new Car();
        car.setBrand("红旗CA72");
        return car;
    }
}
通过工厂类注入bean
<!--工厂类Bean -->
<bean id="carFactory" class="com.smart.ditye.CarFactory" />
<!-- factory-bean指定1处的工厂类Bean;factory-method指定工厂类Bean创建Bean的工厂方法-->
<bean id="car5" factory-bean="carFactory" factory-method="createHongQiCar">
2、静态工厂方法
public class CarFactory{
    public static Car createHongQiCar(){
     ...
    }
}
当使用静态工厂类型的方法后，用户就无须在配置文件定义工厂类的Bean，只需按以下方式进行配置即可：
<bean id="car6" class="com.smart.ditype.CarFactory" factory-method="createHongQiCar" />
直接在<bean>中通过class属性指定工厂类，然后再通过factory-method指定对应的工厂方法。

注入参数详解
1、字面值一般指可用字符串表示的值，这些值可以通过<value>元素标签进行注入。在默认情况下，基本数据类型及其封装类、String等类型都可以采用字面值注入的方式。
<bean id="car" class="com.smart.attr.Car">
 <property name="maxSpeed">
    <value>200</value>
 </property>
 <property name="brand">
    <value><![CDATA[红旗&CA72]]></value>
 </property>
</bean>
<![CDATA[]]的作用是让XML解析器将标签中的字符串当做普通的文本对待。
使用转义可以表示为
<property name="brand">
<value>红旗&amp;CA72</value>
<property>
2、引用其他Bean
public class Boss {
    private Car car;
    public void setCar(Car car){
        this.car = car;
    }
}
boss的bean通过<ref>元素引用car Bean,建立起boss对car的依赖。
<bean id="car" class="com.smart.attr.Car" />
<bean id="boss" class="com.smart.attr.Boss">
 <!--引用上面定义的car Bean-->
  <ref bean="car"></ref>
</bean>
3、内部Bean
<bean id="boss" class="com.smart.attr.Boss">
    <property name="car">
        <bean class="com.smart.attr.Car">
            <property name="maxSpeed" value="200" />
            <property name="price" value="2000.00" />
        </bean>
    </property>
</bean>
4、null值
<bean id="car" class="com.smart.attr.Car">
    <property name="brand"><null/></property>
</bean>
5、级联属性
<bean id="boss3" class="com.smart.attr.Boss">
    <property name="car.brand" value="吉利CT50" />
</bean>
按照上面的配置，Spring将调用Boss.getCar().setBean("吉利CT50")进行属性的注入操作。这时必须对Boss类进行改造，为car属性声明一个初始化对象。
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
6、集合类型属性
List：
public class Boss{
    private List favorites = new ArrayList();
    public List getFavorites(){
        return List;
    }
    public void setFavorites(List favorites){
        this.favorites=favorites;
    }
}
对应的Spring中的属性配置片段
<bean id="boos1" class="com.smart.attr.Boss">
    <property name="favorites">
        <list>
            <value>看报</value>
            <value>赛车</value>
            <value>高尔夫</value>
        </list>
    </property>
</bean>
Set:
<bean id="boos1" class="com.smart.attr.Boss">
    <property name="favorites">
        <set>
            <value>看报</value>
            <value>赛车</value>
            <value>高尔夫</value>
        </set>
    </property>
</bean>
Map:
下面为Boss添加一个Map类型的jobs属性
public class Boss{
    private Map jobs = new HashMap();
    public Map getJobs(){
        return jobs;
    }
    public void setJobs(Map jobs){
        this.jobs=jobs;
    }
}
在配置文件中可以通过以下方式为jobs属性提供配置值：
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
Properties
properties类型是Map类型的特例。Map元素的键和值可以是任何类型的对象，而Properties属性的键和值只能是字符串。
public class Boss{
    private Properties mails = new Properties();
    public Properties getMails(){
     return mails;
    }
    public void setMails(Properties mails){
     this.mails = mails;
    }
}
配置：
<bean id="boss1" class="com.smart.attr.Boss">
    <property name="mails">
        <props>
         <prop key="jobMail">john-office@smart.com</prop>
         <prop key="lifeMail">john-life@smart.com</prop>
        </props>
    </property>
</bean>
强类型集合
public class Boss {
    private Map<String,Integer> jobTime = new HashMap<String,Integer>();
    public Map<String,Integer> getJobTime(){
        return jobTime();
    }
    public void setJobTime(Map<String,Integer> jobTime){
        this.jobTime=jobTime;
    }
}
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
集合合并
Spring支持集合合并的功能，允许子<bean>继承父<beab>的同名属性集合元素，并将子<bean>中配置的集合属性和父<bean>中配置的同名属性值合并起来
作为最终Bean的属性值。
<bean id="parentBoss" abstract="true" class="com.smart.attr.Boss">
    <property name="favorites">
        <set>
         <value>看报</value>
         <value>赛车</value>
         <value>高尔夫</value>
        </set>
    </property>
</bean>
<bean id="childBoss" parent="parentBoss">
    <property name="favorites">
        <set merge="true">
            <value>爬山</value>
            <value>游泳</value>
        </set>
    </property>
</bean>