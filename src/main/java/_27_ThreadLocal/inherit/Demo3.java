package _27_ThreadLocal.inherit;

/**
 * 一旦子线程被创建以后，父子线程之间的ThreadLocal变量就没有关系了，
 * 也就是说，父子关系只存在于创建的那一瞬间
 * 如果子线程可以被复用，那么就会导致线程之间的数据共享，详见Demo4
 */
public class Demo3 {
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        String msg = "hello";
        process(msg);
    }

    private static void process(String msg) throws InterruptedException {
        setMsg(msg);
        printMainMsg();
        Thread thread = new Thread(() -> {
            System.out.println("子线程获取的value: " + threadLocal.get());
            threadLocal.set("world");
            System.out.println("子线程获取的value: " + threadLocal.get());
        });
        thread.start();
        thread.join();
        printMainMsg();
        clearMsg();
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