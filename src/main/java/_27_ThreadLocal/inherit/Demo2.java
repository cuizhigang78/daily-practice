package _27_ThreadLocal.inherit;

/**
 * 使用 InheritableThreadLocal 时，子线程可以获取到父线程的 InheritableThreadLocal 变量
 */
public class Demo2 {
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        String msg = "hello";
        process(msg);
    }

    private static void process(String msg) {
        setMsg(msg);
        printMainMsg();
        printInheritMsg();
        clearMsg();
    }

    private static void printInheritMsg() {
        new Thread(() -> System.out.println("子线程获取的value: " + threadLocal.get())).start();
    }

    private static void printMainMsg() {
        System.out.println("主线程获取的value: " + threadLocal.get());
    }

    private static void setMsg(String msg) {
        threadLocal.set(msg);
    }

    private static void clearMsg() {
        threadLocal.remove();
    }
}