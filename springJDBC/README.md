读取数据库配置的方式：  
```
<!-- 指定spring读取db.properties配置-->
    <context:property-placeholder location="db.properties"/>

    <!--1.将链接池放入spring容器中 -->
    <bean name="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
    destroy-method="close" p:driverClass="${jdbc.driverClass}"
          p:jdbcUrl="${jdbc.jdbcUrl}"
          p:user="${jdbc.user}"
          p:password="${jdbc.password}"
    >
        <!--<property name="driverClass" value="com.mysql.jdbc.Driver"/>-->
        <!--<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/sampledb"/>-->
        <!--<property name="user" value="root"/>-->
        <!--<property name="password" value="123456" />-->

        <!--读取外部配置文件-->
        <!--<property name="driverClass" value="${jdbc.driverClass}"/>-->
        <!--<property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>-->
        <!--<property name="user" value="${jdbc.user}" />-->
        <!--<property name="password" value="${jdbc.password}" />-->

    </bean>
```

数据库事务特性：
原子性、一致性、隔离性和持久性。简称ACID。  
原子性 :强调事务的不可分割.  
一致性 :事务的执行的前后数据的完整性保持一致.  
隔离性 :一个事务执行的过程中,不应该受到其他事务的干扰  
持久性 :事务一旦结束,数据就持久到数据库  

脏读 :一个事务读到了另一个事务的未提交的数据  
不可重复读 :一个事务读到了另一个事务已经提交的 update 的数据导致多次查询结果不一致.   
虚幻读 :一个事务读到了另一个事务已经提交的 insert 的数据导致多次查询结果不一致.  

spring管理事务的属性：  
1.事务的隔离级别  
2.是否只读   
3.事务传播行为:业务方法之间调用，事务应该如何处理  

PROPAGION_XXX :事务的传播行为 * 保证同一个事务中  

PROPAGATION_REQUIRED 支持当前事务，如果不存在 就新建一个(默认)  
PROPAGATION_SUPPORTS 支持当前事务，如果不存在，就不使用事务   
PROPAGATION_MANDATORY 支持当前事务，如果不存在，抛出异常  

PROPAGATION_REQUIRES_NEW 如果有事务存在，挂起当前事务，创建一个新的事务 
PROPAGATION_NOT_SUPPORTED 以非事务方式运行，如果有事务存在，挂起当前事务
PROPAGATION_NEVER 以非事务方式运行，如果有事务存在，抛出异常 
PROPAGATION_NESTED 如果当前事务存在，则嵌套事务执行

Spring管理事务方式：  
1.编码式  
2.xml配置(aop)  
3.注解配置(aop)  

将核心事务管理器配置到spring容器。DataSourceTransactionManager 
SpringAop事务通知  

开启注解配置
@Transaction