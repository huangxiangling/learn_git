package cn.com.sheep.workspace.Abstractdemo;

public class Wine {

    public void fun1() {
        System.out.println("Wine is fun1....");
        fun2();
    }

    public void fun2() {
        System.out.println("Wine is fun2....");
    }
}

class JNC extends Wine {

    /**
     * 子类重写父类的方法
     * 父类中不存在该方法，向上转型后，父类是不能引用该方法的
     *
     * @param a
     */
    public void fun1(String a) {
        System.out.println("JNC is fun1....");
        fun2();
    }

    public void fun2() {
        System.out.println("JNC is fun2....");
    }

    public static void main(String[] args) {
        Wine a = new JNC();
        a.fun1();
    }
}
