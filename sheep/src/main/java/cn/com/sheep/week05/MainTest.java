package cn.com.sheep.week05;

import org.apache.ibatis.javassist.LoaderClassPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

public class MainTest {

    public static void main(String[] args) {


        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Hello hello01 = (Hello) applicationContext.getBean("hello03");
        System.out.println("==== 01 toString :"+hello01.toString());


    }
}
