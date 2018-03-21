package com.smart.beanfactory;

import com.smart.Car;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class BeanFactoryTest {
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resolver =new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:com/smart/beanfactory/beans.xml");
        System.out.println(res.getURI());

        //XmlBeanDefinitionReader 通过 Resource 装载Spring配置信息并启动IoC容器，然后就可以通过
        //BeanFactory#getBean(beanName)方法从IoC容器中获取Bean
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);

        System.out.println("init BeanFactory.");

        Car car = factory.getBean("car",Car.class);
        System.out.println("Car bean is ready for user!");
        car.introduce();


    }
}
