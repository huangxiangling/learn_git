package cn.com.sheep.workspace.zmain;

public class E extends D {

    public E(){

        System.out.println("========= >eeeee");

    }

    public E(String name){

        super(name);
        System.out.println("========= >eeeee");

    }

    public static void main(String[] args) {
        E e = new E("00000");
    }
}
