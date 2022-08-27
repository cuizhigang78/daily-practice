package _29_recursion;

import org.junit.Test;

/**
 * 剖析递归行为时间复杂度的估算
 *
 * master 公式的使用
 *
 * T(N) = a*T(N/b) + O(N^d)
 *
 * 1) log(b, a) > d -> O(N^log(b, a))
 * 2) log(b, a) = d -> O(N^d * logN)
 * 3) log(b, a) < d -> O(N^d)
 */
public class GetMax {

    @Test
    public void test() {
        int[] array = new int[] {2, 1, 5, 3, 8, 7, 6};
        int max = process(array, 0, array.length - 1);
        System.out.println("max = " + max);
    }

    /**
     * 递归找到 array 数组中的最大值
     * @param array 数组
     * @param l 左边界
     * @param r 右边界
     * @return 最大值
     *
     * T(N) = 2*T(N/2) + O(N^0)
     * a = 2, b = 2, d = 0
     * log(a, b) == 1 > 0 -> O(N)
     */
    private static int process(int[] array, int l, int r) {
        if (l == r) return array[l];
        int mid = l + ((r - l) >> 1);
        return Math.max(process(array, l, mid), process(array, mid + 1, r));
    }
}
