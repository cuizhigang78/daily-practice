package _88_stock;

import java.util.ArrayList;
import java.util.List;

public class StockMan {

    public static void main(String[] args) {
        List<Monitor> monitors = new ArrayList<>();
        // 持仓
        List<Monitor> holds = new ArrayList<>();
        // 观望
        List<Monitor> watches = new ArrayList<>();

        // 南极电商 sz002127 6.14
        holds.add(new Monitor("sz002127", 3.615, 5, 6, false, false));
        // 武汉天源 sz301127 15.24
        holds.add(new Monitor("sz301127", 13.266, 5, 5, false, false));
        // 南山智尚 sz300918 16.39
        holds.add(new Monitor("sz300918", 23.502, 5, 7, false, false));
        // 我爱我家 sz000560 3.45
        holds.add(new Monitor("sz000560", 3, 5, 5, false, false));

        // 稳健医疗 sz300888 41.62
        watches.add(new Monitor("sz300888", 39.43, 5, 7, false, false));
        // 双汇发展 sz000895 28.22
        watches.add(new Monitor("sz000895", 25.60, 5, 5, false, false));
        // 合百集团 sz002729 8.46
        watches.add(new Monitor("sz000417", 5.44, 5, 5, false, false));
        // 中国船舶 sh600150
        watches.add(new Monitor("sh600150", 31.5, 50, 50, false, false));
        // 马应龙 sh600993
        watches.add(new Monitor("sh600993", 25, 50, 50, false, false));
        // 九典制药 sz002432 26.53
        watches.add(new Monitor("sz300705", 17, 50, 4, false, false));
        // 长江电力 sh600900 35.31
        // monitors.add(new Monitor("sh600900", 34.5, 50, 0, false, false, false));
        // 富临精工 sz300432 24.11
        // monitors.add(new Monitor("sz300432", 24.11, 50, 0, false, false, false));
        // 九华旅游 sh603199 46.51
        // monitors.add(new Monitor("sh603199", 45.51, 50, 50, false, false, false));
        // 珀莱雅 sh603605 104.88
        // monitors.add(new Monitor("sh603605", 104.88, 50, 0, false, false, false));
        // 中国银河 sh601881 18.20
        // monitors.add(new Monitor("sh601881", 18.20, 50, 0, false, false, false));
        // 中宠股份 sz002891 61.51
        // monitors.add(new Monitor("sz002891", 61.51, 50, 0, false, false, false));
        // 神州泰岳 sz300002 13.65
        // monitors.add(new Monitor("sz300002", 13.65, 50, 0, false, false, false));
        // 恒玄科技 sh688608 504.27
        // monitors.add(new Monitor("sh688608", 504.27, 50, 0, false, false, false));

        holds.forEach(m -> m.setHold(true));
        watches.forEach(m -> m.setHold(false));
        monitors.addAll(holds);
        monitors.addAll(watches);

        monitors.parallelStream().forEach(Monitor::start);
    }
}
