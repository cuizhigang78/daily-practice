package _20_objectMemoryLayout;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

public class ObjectSize {
    public static void main(String[] args) {
        Object obj = new Object();
        // System.out.println(RamUsageEstimator.shallowSizeOf(map));
        // System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
