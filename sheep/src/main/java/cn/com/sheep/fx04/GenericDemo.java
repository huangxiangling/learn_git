package cn.com.sheep.fx04;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericDemo implements Serializable {


    public static void main(String[] args) {

        Demo demo = new Demo();

        Class aClass = demo.getClass();
        // getSuperclass() 获取父类的class
        Class superclass = aClass.getSuperclass();
        System.out.println(superclass);

        // getGenericSuperclass()获得带有泛型的父类
        // Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type genericSuperclass = aClass.getGenericSuperclass();

        System.out.println(genericSuperclass);

        // 参数话类型，既泛型
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        // getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        Class c = (Class) parameterizedType.getActualTypeArguments()[0];

        System.out.println(c);


    }


    public static class Person<T extends Serializable> {

    }

    public static class Demo extends Person<GenericDemo> {

    }
}
