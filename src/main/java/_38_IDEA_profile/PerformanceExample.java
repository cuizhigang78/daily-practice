package _38_IDEA_profile;

import java.util.ArrayList;
import java.util.List;

public class PerformanceExample {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> numbers = generateNumbers(1_000_000);
        int sum = calculateSum(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println("Sum: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

    /**
     * 生成指定数量的数字
     *
     * @param count 数量
     * @return 数字列表
     */
    private static List<Integer> generateNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    /**
     * 计算数字列表的和
     *
     * @param numbers 数字列表
     * @return 和
     */
    private static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}