package _87_Map.hashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapFieldTest {

    @Test
    public void modCount() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        map.forEach((k, v) -> {
            if (1 == k) {
                map.remove(k);
            }
        });
    }
}
