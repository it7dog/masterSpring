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
