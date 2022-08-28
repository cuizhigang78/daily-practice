import org.junit.Test;

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
        int i = 0;
        m(i);
        System.out.println(i);
    }

    private void m(int i) {
        i = 5;
    }
}
