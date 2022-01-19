package cn.com.sheep.workspace.zmain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class A {

    private float f = 1f;
    int m = 12;
    static int n = 1;

    void method(int i){
        System.out.println("int :" + i);
    }

    void method(long i){
        System.out.println("long :" + i);
    }

    public static void main(String[] args) {
        String s1 = "abc";// 常量池中
        String s2 = new String("abc");// 堆内存中
        System.out.println(s1 == s2);// 比较的是地址，所以是false
        System.out.println(s1.equals(s2)); // String 重写equals 和 hashCode 所以为true


        String s3 = "a" + "b" + "c";
        String s4 = "abc";
        System.out.println(s3 == s4); // true
        System.out.println(s3.equals(s4));// true

        String ss = "ab";
        String ss1 = "abc";
        String ss2 = ss + "c";
        System.out.println(ss1 == ss2); // false
        System.out.println(ss1.equals(ss2)); // true

        int[] arr = {1,3,4,5,6,3,7,8,8};
        int[] dest = new int[10];
        arrayCopy(arr,2,dest,2,4);
        for(int i : dest){
            System.out.print(i + " ");
        }

        Map<String, Object> map = new HashMap<>();
        map.put(null,123);


        System.out.println(map.get(null));


        B b = new C();
        b.setAge(1);
        System.out.println(b.toString());


        double abs = Math.abs(1.5);

        if(3 * 0.1 == 0.3){
            System.out.println("=====hahah");
        }else {
            System.out.println("=======false");
        }

        byte i = 127;
        System.out.println("byte:" + i + 1);


        String s = null;


        // 总结&：一个&，条件1为真，需判断条件2； 条件1为假，需判断条件2；
        // 两个&&，条件1为真，需判断条件2；条件1为假，不需要判断条件2；

        // 总结|：一个|，条件1为真，需要判断条件2；条件1为假需要判断条件2；
        // 两个||，条件1为真，不需要判断条件2；条件1为假，需要判断条件2
        System.out.println("sssss  :  ====>" + (s != null));

//        if(s != null && s.length() > 0){
//            System.out.println("=========>");
//        }

        //
        if(s != null || s.length() > 0){

        }

//        if(s == null & s.length() > 0){
//            System.out.println("s == null & s.length() > 0");
//        }

    }


    public static void arrayCopy(int[] src, int srcPos, int[] dest,int destPos, int length){


        for(int i = 0; i < length; i++){
            dest[destPos++] = src[srcPos++];
        }
    }

}
