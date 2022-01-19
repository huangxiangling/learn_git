package cn.com.sheep.workspace.zmain;

public class IntegerTest {

    public static void main(String[] args) {

        test2();

    }

    public static void test() {
        Integer integer = new Integer(100);
        Integer integer1 = new Integer(100);
        System.out.println(integer == integer1);// false  ==比较的是内存地址，new是两个地址
        System.out.println(integer.equals(integer1));// true  Integer重写了equals方法，比较的值相同
    }

    public static void test1() {
        // 自动装箱，如果自动装箱的值是-128~127，则直接从常量池中取，不会创建新的对象，超过这个范围是创建新的对象的。
        Integer integer = 100;  // 在范围内，不会创建新的对象
        Integer integer1 = 100; // 在范围内，不会创建新的对象
        System.out.println(integer == integer1);// true
        System.out.println(integer.equals(integer1));// true
    }

    public static void test2() {
        // 自动装箱，如果自动装箱的值是-128~127，则直接从常量池中取，不会创建新的对象，超过这个范围是创建新的对象的。
        Integer integer = -129;  // 在范围内，不会创建新的对象
        Integer integer1 = -129; // 在范围内，不会创建新的对象
        System.out.println(integer == integer1);// 不在范围，创建了新的对象，所以内存地址不一样 false
        System.out.println(integer.equals(integer1));// 比较的是值 true
    }
}
