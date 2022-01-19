package cn.com.sheep.workspace.reflectdemo;

import java.lang.reflect.Method;

public class ForNameTest {

    public static void main(String[] args) throws Exception {

        // 获得class
        Class<?> aClass = Class.forName("java.lang.String");
        // 获得所有public方法     aClass.getDeclaredMethods()获取该类的所有方法
        Method[] methods = aClass.getMethods();
        // 输出方法名
        for (Method method : methods) {
            System.out.println(method);
        }

    }
}
