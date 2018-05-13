##### 自动化装配 bean
* Spring从两个角度来实现自动化装配：  
1.组件扫描：Spring会自动发现应用上下文中所创建的bean.  
2.自动装配：Spring自动满足bean之间的依赖。  

@ComponentScan：  
这个注解能够在Spring中启用组件扫描。如果没有其他配置的话，@ComponentScan默认会扫描与配置类相同的包。  

如果想更加清晰的表明设置的基础包，可以通过basePackages属性进行配置：  
@ComponentScan(basePackages = "com.inactiom.spring.soundsystem")  
如果有多个基础包，只需要将basePackages属性设置为数组即可：  
@ComponentScan(basePackages = {"soundsystem","video"})  
@ComponentScan还提供了另外一种方式，将其指定为包中所含的类或接口：

  
@Configuration
@ComponentScan(basePackageClasses = {CDPlayer.class,DVDPlayer.class})  

##### 通过java代码装配 bean  
在自动化配置的方式行不通，因此需要明确配置Spring(想要将第三方库中组件装配进来)，javaConfig是更好的方案。  

c-命名空间是在spring3.0中引入的，它是在xml中更为简洁的描述构造器参数的方式。
```
<bean id="cdPlayer" class="soundsystem.CDPlayer" c:cd-ref="compactDisc" />
<bean id="cdPlayer" class="soundsystem.CDPlayer" c:_0-ref="compactDisc" />    
```
##### 导入和混合配置
可以将JavaConfig的组件扫描和自动装配和／或XML配置混合在一起。  
```
@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:cd-config.xml")
public class SoundSystemConfig{
}
```
在JavaConfig配置中，可以使用@Import和@ImportResource来拆分JavaConfig类。在XMl中，我们可以使用<import>元素来拆分XML配置。

