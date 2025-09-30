package _20_objectMemoryLayout;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

public class ObjectSize {
    public static void main(String[] args) {
        Object obj = new Object();
        Map<String, String> map = new HashMap<>();
        // 放1000000个数据
        for (int i = 0; i < 1000000; i++) {
            map.put("key" + i, "value" + i);
        }
        map.entrySet().stream().map(RamUsageEstimator::sizeOf).reduce(Long::sum).ifPresent(System.out::println);
        System.out.println(RamUsageEstimator.sizeOf(obj));
        System.out.println(RamUsageEstimator.shallowSizeOf(obj));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
