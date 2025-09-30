package _85_singleton;

/**
 * 饿汉1
 */
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }
}
