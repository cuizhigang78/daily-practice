package _101_stock;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Monitor> monitors = new ArrayList<>();
        // 中国船舶 sh600150
        monitors.add(new Monitor("sh600150", 35.815));
        // 中银证券 sh601696
        monitors.add(new Monitor("sh601696", 12.004));
        // 国信证券 sz002736
        monitors.add(new Monitor("sz002736", 11.779d));
        // 通威股份 sh600438
        monitors.add(new Monitor("sh600438", 25.941, 5));
        // 南京证券 sh601990
        monitors.add(new Monitor("sh601990", 9.076d));
        // 双汇发展 sz000895 25.414
        monitors.add(new Monitor("sz000895", 25.414d, 2));
        // 南山智尚 sz300918 11.838
        monitors.add(new Monitor("sz300918", 11.838d, 2));
        // 合百集团 sz002729 11.838 6.515
        monitors.add(new Monitor("sz000417", 6.515d, 2));
        monitors.parallelStream().forEach(Monitor::start);
    }
}
