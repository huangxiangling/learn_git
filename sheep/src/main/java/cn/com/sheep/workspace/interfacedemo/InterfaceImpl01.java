package cn.com.sheep.workspace.interfacedemo;

public class InterfaceImpl01 implements InterfaceDemo1, InterfaceDemo2 {
    @Override
    public void print(String name) {
        System.out.println("this is name" + name);
    }
}
