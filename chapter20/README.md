##### TestNG入门——注解之@Test
###### TestNg提供的最基本的注解之一就是Test注解，作用在方法或者类上，此注解支持的属性有:  
1.alwaysRun：提供一个false or true值，如果设置为true，则被标记的方法会永远被执行，即使被标记方法所依赖的方法执行失败了。  
2.dataProvider：此属性的值为标记方法提供数据驱动的数据源。  
3.dataProviderClass：此属性指出提供数据驱动方法的所在类。  
4.dependsOnGroups：此属性指出标记方法所依赖的组。  
5.dependsOnMethods：此属性支持标记方法所依赖的方法。  
6.description：标记方法的描述信息。   
7.enabled：标记方法是否要执行，默认为true执行。  
8.expectedExceptions：指定标记方法返回的异常信息列表。  
9.groups：指定标记方法归属于哪个组。  
10.timeOut：指定标记方法超时时长 (in millisecs)。  
11.invocationCount：被标记的方法会执行多次。 
12.threadPoolSize：启用多个线程执行被标记的方法，一般会与invocationCount一起使用。  

###### Mockito
###### Mock对象用来验证测试中所以依赖对象间的交互是否达到预期。  

@Repository:用于对DAO实现类进行标注  
@Service:用于对Service实现类进行标注。  
@Controller:用于对Controller实现类进行标注。 

##### 事物注解方式: @Transactional

>     
当标于类前时, 标示类中所有方法都进行事物处理 , 例子: 
```
 @Transactional public class TestServiceBean implements TestService {}
```
>     
当类中某些方法不需要事物时:
```
@Transactional  
 public class TestServiceBean implements TestService { 
      private TestDao dao; 
      public void setDao(TestDao dao) { 
          this.dao = dao; 
      } 
     @Transactional(propagation =Propagation.NOT_SUPPORTED)
      public List getAll() { 
          return null; 
    }  }
```

注意的几点:  
 1.@Transactional 只能被应用到public方法上, 对于其它非public的方法,如果标记了@Transactional也不会报错,但方法没有事务功能.  
 2.用 spring 事务管理器,由spring来负责数据库的打开,提交,回滚.默认遇到运行期例外(throw new RuntimeException("注释");)会回滚，即遇到不受检查（unchecked）的例外时回滚；而遇到需要捕获的例外(throw new Exception("注释");)不会回滚,即遇到受检查的例外（就是非运行时抛出的异常，编译器会检查到的异常叫受检查例外或说受检查异常）时，需我们指定方式来让事务回滚要想所有异常都回滚,要加上 @Transactional( rollbackFor={Exception.class,其它异常}) .如果让unchecked例外不回滚： @Transactional(notRollbackFor=RunTimeException.class)
 ```
 @Transactional(rollbackFor=Exception.class) //指定回滚,遇到异常Exception时回滚
 public void methodName() {
 　　　throw new Exception("注释");
 }
 @Transactional(noRollbackFor=Exception.class)//指定不回滚,遇到运行期例外(throw new RuntimeException("注释");)会回滚
 public ItimDaoImpl getItemDaoImpl() {
 　　　throw new RuntimeException("注释");
 }
 ```
 3.@Transactional 注解应该只被应用到 public 可见度的方法上。 如果你在 protected、private 或者 package-visible 的方法上使用 @Transactional 注解，它也不会报错， 但是这个被注解的方法将不会展示已配置的事务设置。 
 4.@Transactional 注解可以被应用于接口定义和接口方法、类定义和类的 public 方法上。然而，请注意仅仅 @Transactional 注解的出现不足于开启事务行为，它仅仅 是一种元数据，能够被可以识别 @Transactional 注解和上述的配置适当的具有事务行为的beans所使用。上面的例子中，其实正是 元素的出现 开启 了事务行为。  
 5.Spring团队的建议是你在具体的类（或类的方法）上使用 @Transactional 注解，而不要使用在类所要实现的任何接口上。你当然可以在接口上使用 @Transactional 注解，但是这将只能当你设置了基于接口的代理时它才生效。因为注解是不能继承的，这就意味着如果你正在使用基于类的代理时，那么事务的设置将不能被基于类的代理所识别，而且对象也将不会被事务代理所包装（将被确认为严重的）。因此，请接受Spring团队的建议并且在具体的类上使用 @Transactional 注解。  
 