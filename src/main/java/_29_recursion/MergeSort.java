package _29_recursion;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName MergeSort 归并排序
 * @Author Maxwell
 * @Date 2022/8/28 1:01
 * @Description MergeSort
 * @Version 1.0
 */
public class MergeSort {

    @Test
    public void sort() {
        int[] array = new int[] {2, 1, 6, 3, 5, 4};
        process(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private void process(int[] array, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(array, l, mid);
        process(array, mid + 1, r);
        merge(array, l, mid, r);

        /**
         * T(N) = 2 * T(N/2) + O(N ^ 1)
         *
         * a = 2, b = 2, d = 1
         *
         * log(b, a) = d -> O(N^d * logN) = O(N*logN)
         */
    }

    private void merge(int[] array, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            if (array[p1] <= array[p2]) {
                help[i] = array[p1];
                p1++;
            } else {
                help[i] = array[p2];
                p2++;
            }
            i++;
        }
        while (p1 <= mid) {
            help[i++] = array[p1++];
        }
        while (p2 <= r) {
            help[i++] = array[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            array[l + j] = help[j];
        }
    }
}
