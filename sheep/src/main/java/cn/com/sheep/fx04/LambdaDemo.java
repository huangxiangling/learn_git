package cn.com.sheep.fx04;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class LambdaDemo<T extends Serializable & Comparable & Collection> {


    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo();

        MathOperation op = new MathOperation<Integer>() {
            @Override
            public Integer operation(int a, int b) {
                return 1;
            }
        };

        MathOperation op1 = (a, b) -> 1;

        MathOperation op2 = new MathOperation<Integer>() {
            @Override
            public Integer operation(int a, int b) {
                return a + b;
            }
        };

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation addition1 = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation addition2 = (a, b) -> {
            int c = 100;
            return a * b + c;
        };
        MathOperation division = (a, b) -> a / b;

        System.out.println("10 + 5 = " + demo.operate(10, 5, addition));
        System.out.println("10 - 5 = " + demo.operate(10, 5, addition1));
        System.out.println("10 x 5 = " + demo.operate(10, 5, addition2));
        System.out.println("10 / 5 = " + demo.operate(10, 5, division));
        System.out.println("10 ^ 5 = " + demo.operate(10, 5, (a, b) -> Math.pow(a, b)));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) -> {
            System.out.println(message);
        };

        GreetingService greetService3 = System.out::println;

        Arrays.asList(1, 2, 3).forEach(x -> System.out.println(x + 3));
        Arrays.asList(1, 2, 3).forEach(LambdaDemo::println);

        greetService1.sayMessage("kimmking");
        greetService2.sayMessage("Java");
        greetService3.sayMessage("CuiCuilaoshi");


    }

    private static void println(int x) {
        System.out.println(x + 3);
    }

    interface MathOperation<T> {
        // 返回类型+函数名+参数类型的列表
        T operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private <T> T operate(int a, int b, MathOperation<T> mathOperation) {
        return mathOperation.operation(a, b);
    }
}
