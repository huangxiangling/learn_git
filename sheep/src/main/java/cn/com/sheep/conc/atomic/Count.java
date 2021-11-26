package cn.com.sheep.conc.atomic;

public class Count {
    private int num = 0;
    public int add(){
        synchronized (this){
            num++;
        }
        return num;
    }
    public int getNum(){
        return num;
    }
}
