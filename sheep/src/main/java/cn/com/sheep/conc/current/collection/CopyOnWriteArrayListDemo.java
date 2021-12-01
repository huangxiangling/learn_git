package cn.com.sheep.conc.current.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {


    public static class T1 extends Thread {
        private List<Integer> list;

        public T1(List<Integer> list) {
            this.list = list;
        }

        public void run() {
            for (int i = 0; i < list.size(); i++) {

            }
        }
    }

    public static class T2 extends Thread {
        private List<Integer> list;

        public T2(List<Integer> list) {
            this.list = list;
        }

        public void run() {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("t2========>" + list.get(i));
                list.remove(i);
            }
        }
    }

    public static void main(String[] args) {

        // ArrayList，LinkedList，Vector不安全，运行报错
//        List<Integer> list = new ArrayList<>();
//        List<Integer> list = new LinkedList<>();
//        List<Integer> list = new Vector<>();

        // 只有CopyOnWriteArrayList 安全，不报错
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        T1 t1 = new T1(list);
        T2 t2 = new T2(list);
        t1.start();
        t2.start();

        try {
            Thread.sleep(2000);
            for (int i = 0; i < list.size(); i++) {
                System.out.println("=======> " + list.get(i));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
