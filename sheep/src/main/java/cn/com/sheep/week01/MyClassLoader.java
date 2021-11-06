package cn.com.sheep.week01;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Class<?> aClass = new MyClassLoader().findClass("Hello");
            Method method = aClass.getMethod("hello");
            method.invoke(aClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SneakyThrows
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get("src/main/java/cn/com/sheep/week01/Hello.xlass");
        byte[] bytes = Files.readAllBytes(path);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
