package _101_stock;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Monitor> monitors = new ArrayList<>();
        // 中国船舶 sh600150
        monitors.add(new Monitor("sh600150", 35.702));
        // 片仔癀 sh600436 10.5
        // monitors.add(new Monitor("sh600436", 378.703, 100, 100));
        // 国信证券 sz002736
        monitors.add(new Monitor("sz002736", 11.554d));
        // 中银证券 sh601696
        monitors.add(new Monitor("sh601696", 12.004));
        // 南山智尚 sz300918 11.838
        monitors.add(new Monitor("sz300918", 11.603d, 10, 5, false));
        // 通威股份 sh600438
        monitors.add(new Monitor("sh600438", 23.7));
        // 合百集团 sz002729 11.838 6.515
        monitors.add(new Monitor("sz000417", 6.568d, 2, 5, true));
        // 南极电商 sz002127 5.041
        monitors.add(new Monitor("sz002127", 4.925d, 2, 5, true));
        // 九典制药 sz002432 24.65
        monitors.add(new Monitor("sz300705", 24.546d, 10, 5, true));
        // 武商集团 sz000501 10.691
        monitors.add(new Monitor("sz000501", 10.426d));
        // 金螳螂 sz002081 4.08
        monitors.add(new Monitor("sz002081", 4.04d));
        // 双汇发展 sz000895 25.414
        monitors.add(new Monitor("sz000895", 25.413d, 10, 2, false));
        // 中船科技 sh600072 14.12
        monitors.add(new Monitor("sh600072", 14.12d, true));
        monitors.parallelStream().forEach(Monitor::start);
    }
}
