package cn.com.sheep.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sheepcolor {


    private volatile long count = 0;
    private void add10k(){
        int idx = 0;
        while(idx ++ < 1000){
            count += 1;
        }
    }

    public static long calc(){
        final Sheepcolor test = new Sheepcolor();
        try {
            // 创建两个线程，执行add()操作
            Thread th1 = new Thread(() -> {
                test.add10k();
            });
            Thread th2 = new Thread(() -> {
                test.add10k();
            });
            // 启动两个线程
            th1.start();
            th2.start();
            // 等待两个线程执行结束
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return test.count;
    }

    public static void main(String[] args) {

        String s = "abcd";
        StringBuffer buffer = new StringBuffer(s);
        StringBuffer reverse = buffer.reverse();
        System.out.println(reverse.toString());
        List<String> strings = new ArrayList<>();

        strings.add("123");
        Collections.unmodifiableCollection(strings);
    }
}
