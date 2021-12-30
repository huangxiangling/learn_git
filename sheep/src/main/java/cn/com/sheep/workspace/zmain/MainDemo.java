package cn.com.sheep.workspace.zmain;

import cn.com.sheep.workspace.service.HelloFunction;
import io.netty.util.internal.MathUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainDemo {

    public static void main(String[] args) {


        try {
            Thread.sleep(1000);
            System.out.println("====000");
            add();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("=============> 111");
        }

        System.out.println("============222");
    }



    public static HelloFunction add(){

        try {
            int i = 0;
            i = 1 /0;
        } finally {
            System.out.println("=====55555");
        }
        return null;
    }

    public static HelloFunction multiplication(){
        return (a, b) -> a * b;
    }
}
