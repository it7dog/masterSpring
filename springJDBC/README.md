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