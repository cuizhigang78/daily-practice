package _27_ThreadLocal.inherit;

/**
 * 使用 ThreadLocal 时，子线程无法获取到父线程的 ThreadLocal 变量
 */
public class Demo1 {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

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