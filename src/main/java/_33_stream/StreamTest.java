package _33_stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    /**
     * Steam filter后集合修改
     *
     * 由于过滤后的集合中，保存的是对象的引用，当时可能只是想修改过滤后的数据，但实际上，你会把元素数据一同修改了。
     */
    @Test
    public void test1() {

        List<StringBuilder> list = Lists.newArrayList(new StringBuilder("a"), new StringBuilder("b"));

        List<StringBuilder> filterList = list.stream().filter(v -> "a".equalsIgnoreCase(v.toString())).collect(Collectors.toList());

        for (StringBuilder v : filterList) {
            v.append("b");
        }

        System.out.println(list);
        // [ab, b]
    }
}
