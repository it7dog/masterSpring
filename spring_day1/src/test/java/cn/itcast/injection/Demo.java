package cn.itcast.injection;

import cn.itcast.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    @Test
    public void fun1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("injection1/applicationContext.xml");
        User user = (User) ac.getBean("user4");
        System.out.println(user);

    }

    @Test
    public void fun2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("injection1/applicationContext.xml");
        CollectionBean bean = (CollectionBean)ac.getBean("cb");
        System.out.println(bean.toString());

    }




}
