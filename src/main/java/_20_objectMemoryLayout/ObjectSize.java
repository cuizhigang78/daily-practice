package _20_objectMemoryLayout;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

public class ObjectSize {
    public static void main(String[] args) {
        Object obj = new Object();
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        System.out.println(RamUsageEstimator.sizeOf(obj));
        System.out.println(RamUsageEstimator.shallowSizeOf(obj));
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
