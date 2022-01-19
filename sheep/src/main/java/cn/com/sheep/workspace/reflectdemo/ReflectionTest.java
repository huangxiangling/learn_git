package cn.com.sheep.workspace.reflectdemo;

import lombok.Data;

import java.lang.reflect.Field;

public class ReflectionTest {

    public static void main(String[] args) throws Exception {

        DisPlay disPlay = new DisPlay();
        Class<? extends DisPlay> aClass = disPlay.getClass();
        aClass.getMethod("show", int.class).invoke(new DisPlay(), 12);

        // 反射之属性练习
        Student student = new Student();
        student.setStuName("huangxiangling");
        student.setStuAge(20);


    }


    public static Object copy(Object source) throws Exception {
        // 取得拷贝源对象的Class对象
        Class<?> aClass = source.getClass();
        // 取得拷贝源对象的属性列表
        Field[] fields = aClass.getDeclaredFields();
        // 取得拷贝目标对象的Class对象
        Object newInstance = aClass.newInstance();

        for (Field field : fields) {
            // 设置属性的可访问性
            field.setAccessible(true);
            field.set(newInstance, field.get(source));
        }
        return newInstance;
    }
}


class DisPlay {

    public void show(String name) {
        System.out.println(name);
    }

    public void show(int a) {
        System.out.println(a);
    }

    public String show(boolean flag) {
        return flag ? "true" : "false";
    }
}

@Data
class Student {

    private String stuName;
    private int stuAge;
}