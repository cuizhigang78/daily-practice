package _28_delay_queue;

import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class DelayQueueTest {
    @Test
    public void test1() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        int numberOfElementsToProduce = 2;
        int delayOfEachProducedMessageMilliseconds = 3_000;
        // 生产者将 put() 两个对象以 5000 毫秒的延迟放入队列。 该测试断言消费者消费了两条消息：
        DelayQueueProducer producer = new DelayQueueProducer(queue, numberOfElementsToProduce, delayOfEachProducedMessageMilliseconds);
        DelayQueueConsumer consumer = new DelayQueueConsumer(queue, numberOfElementsToProduce);

        executor.submit(producer);
        executor.submit(consumer);

        // 5s 后终止线程池
        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();
        assertEquals(consumer.numberOfConsumedElements.get(), numberOfElementsToProduce);
    }

}
