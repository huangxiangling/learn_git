package cn.com.sheep.workspace.gof23.abstract_factory;

public class ConcreteFactory1 implements AbstractFactory {

    public Product1 newProduct1() {
        System.out.println("具体工厂 1 生成-->具体产品 11...");
        return new ConcreteProduct11();
    }

    public Product2 newProduct2() {
        System.out.println("具体工厂 1 生成-->具体产品 21...");
        return new ConcreteProduct21();
    }

}


class Product1 {

}

class Product2 {

}

class ConcreteProduct11 extends Product1 {

}

class ConcreteProduct21 extends Product2 {

}

