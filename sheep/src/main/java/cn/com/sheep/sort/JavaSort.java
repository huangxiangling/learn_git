package cn.com.sheep.sort;

import java.util.ArrayList;
import java.util.List;

public class JavaSort {


    /**
     * 归并排序
     *
     * @param a
     * @param low
     * @param high
     * @return
     */
    public static int[] sort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            sort(a, low, mid);
            sort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
        return a;
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }


    /**
     * 堆排序
     *
     * @param arr
     */
    public static void myHeapSort(int[] arr) {
        int i = 0;
        int len = arr.length;
        for (i = len / 2 - 1; i >= 0; i--) {
            adjustment(arr, i, len);
        }
        for (i = len - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustment(arr, 0, i);
        }

    }

    private static void adjustment(int[] arr, int pos, int len) {
        int child = 2 * pos + 1;
        if (child + 1 < len && arr[child] > arr[child + 1]) {
            child++;
        }
        if (child < len && arr[pos] > arr[child]) {
            int temp = arr[pos];
            arr[pos] = arr[child];
            arr[child] = temp;
            adjustment(arr, child, len);
        }
    }

    /**
     * 基数排序
     *
     * @param arr
     */
    public static void basicSort(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        }

        List<ArrayList> queen = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList q = new ArrayList<>();
            queen.add(q);
        }

        for (int i = 0; i < times; i++) {
            for (int j = 0; j < arr.length; j++) {
                int x = arr[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList q = queen.get(x);
                q.add(arr[j]);
            }
            int count = 0;
            for (int z = 0; z < 10; z++) {
                while (queen.get(z).size() > 0) {
                    ArrayList<Integer> c = queen.get(z);
                    arr[count] = c.get(0);
                    c.remove(0);
                    count++;
                }
            }
        }
    }


    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int t = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
            }
        }
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        int pivot, p_pos, i, t;
        if (low < high) {
            p_pos = low;
            pivot = arr[p_pos];
            for (i = low + 1; i < high; i++) {
                if (arr[i] > pivot) {
                    p_pos++;
                    t = arr[p_pos];
                    arr[p_pos] = arr[i];
                    arr[i] = t;
                }
                t = arr[low];
                arr[low] = arr[p_pos];
                arr[p_pos] = t;
                quickSort(arr, low, p_pos + 1);
                quickSort(arr, p_pos + 1, high);
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                int value = arr[i];
                int j;
                for (j = i - step; j >= 0 && arr[j] > value; j = step) {
                    arr[j + step] = arr[j];
                }
                arr[j + step] = value;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param ins
     */
    public static void insertionSort(int[] ins) {
        for (int i = 1; i < ins.length; i++) {
            for (int j = i; j > 0; j--) {
                if (ins[j] < ins[j - 1]) {
                    int temp = ins[j - 1];
                    ins[j - 1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        int t = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
                if (index != i) {
                    t = arr[i];
                    arr[i] = arr[index];
                    arr[index] = t;
                }
            }
        }
    }
}
