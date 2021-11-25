package cn.com.sheep.sort;

import java.util.Arrays;

public class SortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 6, 9945, 12, 33};
//        JavaSort.basicSort(arr);
        JavaSort.quickSort(arr, 2, 5);
        for (int a : arr) {
            System.out.println("====>" + a);
        }

    }
}
