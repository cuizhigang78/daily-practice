package _87_Map.hashMap;

import org.junit.Test;

import java.util.*;

public class HashMapPutDemo {

    @Test
    public void test1() {
        Map<String, Object> hashMap = new HashMap<>(2);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    hashMap.put(UUID.randomUUID().toString(), "");
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(hashMap.size());
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }

    /**
     * HashMap的容量大小必是2的整数次幂，即使在创建HashMap对象时指定了容量的大小，
     * 也会调用tableSizeFor()方法来获取大于指定容量的最小的2的整数次幂。
     * 这么做的目的是为了能使HashMap中的元素均匀地分布，降低hash碰撞的可能。因为
     * hashMap储存元素时，生成下标的算法是通过（map的容量-1 & hash值），此时，下
     * 标只与hash值有关，从而实现了均匀分布。
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n = n | n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1 << 30) ? 1 << 30 : n + 1;
    }
}
