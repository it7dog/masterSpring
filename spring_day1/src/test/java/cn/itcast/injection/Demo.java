package cn.itcast.injection;

import cn.itcast.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//创建测试容器
@RunWith(SpringJUnit4ClassRunner.class)
//指定创建容器使用哪个配置文件
@ContextConfiguration("classpath:injection1/applicationContext.xml")
public class Demo {

    @Resource(name="user")
    private User u;
    /*@Test
    public void fun1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("injection1/applicationContext.xml");
        User user = (User) ac.getBean("user4");
        System.out.println(user);

    }*

   /* @Test
    public void fun2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("injection1/applicationContext.xml");
        CollectionBean bean = (CollectionBean) ac.getBean("cb");
        System.out.println(bean.toString());
    }
*/
    @Test
    public void fun1(){
        System.out.println(u);
    }
}
