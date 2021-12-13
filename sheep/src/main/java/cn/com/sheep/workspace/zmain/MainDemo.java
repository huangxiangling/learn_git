package cn.com.sheep.workspace.zmain;

import cn.com.sheep.workspace.service.HelloFunction;
import io.netty.util.internal.MathUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainDemo {

    public static void main(String[] args) {

        System.out.println(add().operation(1,9));
        System.out.println(multiplication().operation(1,9));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 8);
        Integer integer = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(integer);

    }



    public static HelloFunction add(){
        return (a, b) -> a + b;
    }

    public static HelloFunction multiplication(){
        return (a, b) -> a * b;
    }
}
