import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Main
 * @Author Maxwell
 * @Date 2022/8/21 17:06
 * @Description Main
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("hello world");
            TimeUnit.SECONDS.sleep(1);
            // 下午3点后自动退出
            if (LocalDateTime.now().getHour() >= 15) {
                System.out.println("下午3点后自动退出");
                break;
            }
        }
    }
}