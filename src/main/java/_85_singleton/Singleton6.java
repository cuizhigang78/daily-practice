package _85_singleton;

/**
 * 懒汉3
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象。
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，
 * 只有在被调用时才会加载和初始化的。
 */
public class Singleton6 {
    private Singleton6() {

    }

    private static class Inner {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return Inner.INSTANCE;
    }
}
