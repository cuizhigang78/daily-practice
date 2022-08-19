package _28_delay_queue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class DelayQueueProducer implements Runnable {

    private final BlockingQueue<DelayObject> queue;
    /**
     * 要生产的元素数量
     */
    private final int numberOfElementsToProduce;
    /**
     * 延迟毫秒数
     */
    private final int delayOfEachProducedMessageMilliseconds;


    public DelayQueueProducer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToProduce, Integer delayOfEachProducedMessageMilliseconds) {
        this.queue = queue;
        this.numberOfElementsToProduce = numberOfElementsToProduce;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToProduce; i++) {
            DelayObject object = new DelayObject(UUID.randomUUID().toString(), delayOfEachProducedMessageMilliseconds);
            System.out.println("放入对象: " + object);
            try {
                queue.put(object);
                // Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}

