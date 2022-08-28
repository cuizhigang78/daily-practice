package _29_recursion;

import org.junit.Test;

/**
 * 小和问题
 * <p>
 * 描述
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * <p>
 * 例子
 * [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1,3
 * 2左边比2小的数：1
 * 5左边比5小的数：1,3,4,2
 * 所以小和为1+1+3+1+1+3+4+2=16
 * <p>
 * 在归并排序的基础上，每次合并两个有序数组时，判断是否产生小和。
 */
public class SmallSum {

    @Test
    public void test() {
        int[] array = new int[]{1, 3, 4, 2, 5};
        int res = process(array, 0, array.length - 1);
        System.out.println("小和结果为： " + res);
    }

    public int process(int[] array, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(array, l, mid) +
                process(array, mid + 1, r) +
                merge(array, l, mid, r);
    }

    public int merge(int[] array, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int res = 0;
        int p1 = l, p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            if (array[p1] < array[p2]) {
                help[i] = array[p1];
                for (int j = p2; j <= r; j++) {
                    res += array[p1];
                }
                p1++;
            } else {
                help[i] = array[p2];
                p2++;
            }
            i++;
        }
        while (p1 <= mid) {
            help[i] = array[p1];
            i++;
            p1++;
        }
        while (p2 <= r) {
            help[i] = array[p2];
            i++;
            p2++;
        }
        for (int k = 0; k < help.length; k++) {
            array[l + k] = help[k];
        }
        return res;
    }
}
