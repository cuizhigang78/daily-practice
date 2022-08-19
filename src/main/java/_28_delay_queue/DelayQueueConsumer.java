package _28_delay_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayQueueConsumer implements Runnable {
    private final BlockingQueue<DelayObject> queue;
    private final int numberOfElementsToTake;
    public AtomicInteger numberOfConsumedElements = new AtomicInteger();


    public DelayQueueConsumer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToTake) {
        this.queue = queue;
        this.numberOfElementsToTake = numberOfElementsToTake;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToTake; i++) {
            try {
                DelayObject object = queue.take();
                numberOfConsumedElements.incrementAndGet();
                System.out.println("Consumer take: " + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
