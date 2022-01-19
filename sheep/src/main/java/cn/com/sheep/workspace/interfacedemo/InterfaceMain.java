package cn.com.sheep.workspace.interfacedemo;

public class InterfaceMain {

    public static void main(String[] args) {

        InterfaceDemo1 interfaceDemo1 = new InterfaceImpl01();
        InterfaceDemo2 interfaceDemo2 = new InterfaceImpl01();
        interfaceDemo1.print("interfaceDemo1");
        interfaceDemo2.print("interfaceDemo2");

    }
}
