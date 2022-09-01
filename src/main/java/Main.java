import _23_spring.A;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Main
 * @Author Maxwell
 * @Date 2022/8/21 17:06
 * @Description Main
 * @Version 1.0
 */
public class Main {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    private void m(int i) {
        i = 5;
    }
}
