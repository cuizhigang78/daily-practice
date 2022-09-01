package _09_fork_join_pool;

import lombok.SneakyThrows;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ForkJoinPoolTest {

    @SneakyThrows
    @Test
    public void test() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                4,
                4,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(8),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        MyCallable callable = new MyCallable("1", threadPool, 0);
        List<MyCallable> dependencies = new ArrayList<>();
        callable.dependencies = dependencies;

        MyCallable myCallable2 = new MyCallable("2", threadPool, 1);
        myCallable2.dependencies = Collections.singletonList(new MyCallable("7", threadPool, 1));
        dependencies.add(myCallable2);

        MyCallable myCallable3 = new MyCallable("3", threadPool, 1);
        myCallable3.dependencies = Collections.singletonList(new MyCallable("8", threadPool, 1));
        dependencies.add(myCallable3);

        MyCallable myCallable4 = new MyCallable("4", threadPool, 1);
        myCallable4.dependencies = Collections.singletonList(new MyCallable("9", threadPool, 1));
        dependencies.add(myCallable4);

        MyCallable myCallable5 = new MyCallable("5", threadPool, 5);
        dependencies.add(myCallable5);

        MyCallable myCallable6 = new MyCallable("6", threadPool, 5);
        dependencies.add(myCallable6);

        System.out.println("start: " + LocalDateTime.now());
        Future<Boolean> f = threadPool.submit(callable);
        f.get();
        System.out.println("end:  " + LocalDateTime.now());
    }

    static class MyCallable implements Callable<Boolean> {

        private String threadName;

        private ExecutorService threadPool;

        private List<MyCallable> dependencies = new ArrayList<>();

        private long elapsed;

        public MyCallable() {
        }

        public MyCallable(String threadName, ExecutorService threadPool, long elapsed) {
            this.threadName = threadName;
            this.elapsed = elapsed;
            this.threadPool = threadPool;
        }

        @Override
        public Boolean call() throws Exception {

            System.out.println(LocalDateTime.now() + "\t\t" + this.threadName + "\t\t" + Thread.currentThread().getName() + "\t" + "come in...");

            List<Future<Boolean>> futures = dependencies.stream().map(myCallable -> threadPool.submit(myCallable)).collect(Collectors.toList());

            for (Future<Boolean> future : futures) {
                future.get();
            }

            TimeUnit.SECONDS.sleep(elapsed);

            return false;
        }
    }
}
