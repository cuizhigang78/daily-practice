package _27_ThreadLocal;

import java.util.Map;
import java.util.HashMap;

public class WithInitialExample {
    private static final ThreadLocal<Map<Integer, String>> threadLocalMap = ThreadLocal.withInitial(HashMap::new);

    public static void main(String[] args) {
        // 获取初始值
        Map<Integer, String> initialMap = threadLocalMap.get();
        System.out.println("Initial map: " + initialMap);

        // 设置值
        initialMap.put(1, "value1");
        System.out.println("Map after setting value: " + threadLocalMap.get());

        // 移除值
        threadLocalMap.remove();
        System.out.println("Map after remove: " + threadLocalMap.get());
    }
}