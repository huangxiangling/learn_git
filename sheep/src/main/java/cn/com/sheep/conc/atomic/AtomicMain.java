package cn.com.sheep.conc.atomic;

public class AtomicMain {



    public static void main(String[] args) {

//        SyncCount count = new SyncCount();
//        Count count = new Count();
        AtomicCount count = new AtomicCount();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    count.add();
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num = " + count.getNum());
    }
}
