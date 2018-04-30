package cn.itcast.test;

import cn.itcast.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    @Test
    public void fun1() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) ac.getBean("user");
        user.setAge(12);
        user.setName("aaa");
        System.out.println(user);

        //User user2 = (User) ac.getBean("user");

        //User user3 = (User) ac.getBean("user");

        //System.out.println( user2 == user3);
        //容器销毁方法
        ac.close();

    }

    @Test
    public void fun2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/b_create/applicationContext.xml");
        User user = (User) ac.getBean("user2");
        user.setAge(12);
        user.setName("aaa");
        System.out.println(user);

    }

    @Test
    public void fun3() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/b_create/applicationContext.xml");
        User user = (User) ac.getBean("user3");
        user.setAge(12);
        user.setName("aaa");
        System.out.println(user);

    }

    @Test
    public void fun4() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/injection/applicationContext.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);

    }

}
