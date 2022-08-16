package _20_objectMemoryLayout;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

public class ObjectSize {
    public static void main(String[] args) {
        int[] array = new int[3];
        System.out.println(RamUsageEstimator.shallowSizeOf(array));

        System.out.println(ClassLayout.parseInstance(array).toPrintable());
    }
}
