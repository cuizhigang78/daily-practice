package _20_objectMemoryLayout;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ObjectSize
 * @Author Maxwell
 * @Date 2022/8/16 0:08
 * @Description ObjectSize
 * @Version 1.0
 */
public class ObjectSize {
    public static void main(String[] args) {
        int[] array = new int[3];
        System.out.println(RamUsageEstimator.shallowSizeOf(array));

        System.out.println(ClassLayout.parseInstance(array).toPrintable());
    }
}
