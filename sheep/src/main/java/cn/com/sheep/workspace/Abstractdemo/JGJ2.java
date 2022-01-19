package cn.com.sheep.workspace.Abstractdemo;

public class JGJ2 extends Wine2 {

    public JGJ2() {
        setName("JGJ2");
    }

    @Override
    public String drink() {
        return "喝的是：" + getName();
    }
}
