package cn.itcast.test;

import cn.itcast.bean.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo2 {
    @Test
    public void fun1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/injection/applicationContext.xml");
        User user = (User) ac.getBean("user");
        System.out.println(user);

    }



}
