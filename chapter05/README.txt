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