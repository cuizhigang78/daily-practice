package _85_singleton;

/**
 * 懒汉2 线程安全版
 */
public class Singleton5 {
    private static Singleton5 instance;
    private Singleton5() {

    }
    public static Singleton5 getInstance() {
        if(instance == null) {
            synchronized (Singleton5.class) {
                if(instance == null) {
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }
}
