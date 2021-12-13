package cn.com.sheep.fx04;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(4, 2, 3, 5, 1, 2, 2, 7, 6);
        print(list);

        // 集合按从小到大排序
        Collections.sort(list);
        print(list);

        // 反转集合的顺序
        Collections.reverse(list);
        print(list);

        // 返回指定集合中与指定对象相等的元素数
        int frequency = Collections.frequency(list, 2);
        System.out.println(frequency);

        // 取集合中的最大值
        Integer max = Collections.max(list);
        System.out.println(max);

        // 集合洗牌，随机排列
        Collections.shuffle(list);
        print(list);

        // 将19替换所有的集合元素
        Collections.fill(list, 19);
        print(list);


    }


    private static void print(List<Integer> list) {

        String join = String.join(",", list.stream().map(i -> i.toString()).collect(Collectors.toList()).toArray(new String[]{}));
        System.out.println(join);

    }
}
