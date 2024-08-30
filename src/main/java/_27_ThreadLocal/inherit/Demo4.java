package _27_ThreadLocal.inherit;

import java.util.concurrent.*;


/**
 * 使用 InheritableThreadLocal 时，子线程可以获取到父线程的 InheritableThreadLocal 变量
 * 但是，如果使用线程池，因为线程池中的线程是复用的，所以可能会导致线程之间的数据共享
 */
public class Demo4 {
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    private static final ExecutorService threadPool = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        // 假设有10个线程调用 process 方法
        for (int i = 0; i < 10; i++) {
            String msg = "hello: " + i;
            new Thread(() -> process(msg), "thread" + i).start();
        }
        // shutdown();
    }

    private static void process(String msg) {
        setMsg(msg);
        printMainMsg();
        printInheritMsg();
        clearMsg();
    }

    private static void printInheritMsg() {
        threadPool.execute(() -> System.out.println("子线程" + Thread.currentThread().getName() + "获取的value: " + threadLocal.get()));
    }

    private static void printMainMsg() {
        System.out.println("主线程" + Thread.currentThread().getName() + "获取的value: " + threadLocal.get());
    }

    private static void setMsg(String msg) {
        threadLocal.set(msg);
    }

    private static void clearMsg() {
        threadLocal.remove();
    }

    private static void shutdown() {
        if (!threadPool.isShutdown()) { // 确保不是已经关闭的
            threadPool.shutdown(); // 尝试关闭
            try {
                if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) { // 等待任务完成
                    threadPool.shutdownNow(); // 超时则尝试立即关闭
                }
            } catch (InterruptedException e) {
                threadPool.shutdownNow(); // 处理中断异常
                Thread.currentThread().interrupt(); // 重新中断当前线程
            }
        }
    }
}