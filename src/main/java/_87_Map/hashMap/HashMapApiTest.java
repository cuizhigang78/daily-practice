package _87_Map.hashMap;

import java.util.HashMap;
import java.util.Map;

public class HashMapApiTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        // System.out.println(map);

        map.computeIfPresent(null, (k, v) -> v.toUpperCase());
        System.out.println(map);
    }
}
