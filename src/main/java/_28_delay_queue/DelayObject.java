package _28_delay_queue;

import com.google.common.primitives.Ints;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayObject implements Delayed {

    private final String data;
    // 从队列中消耗元素的时间
    private final long startTime;

    public DelayObject(String data, long delayInMilliseconds) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delayInMilliseconds;
    }

    /**
     * 在给定的时间单位内返回与此对象关联的剩余延迟。
     *
     * 当消费者尝试从队列中取出一个元素时，DelayQueue 将执行 getDelay() 以确定是否允许从队列中返回该元素。
     * 如果 getDelay() 方法将返回零或负数，则表示可以从队列中检索它。
     *
     * 当 Delayed 消息 getDelay() 方法的实现返回一个负数时，这意味着给定的元素已经过期。
     * 在这种情况下，生产者将立即消耗该元素。
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        // 时间戳转其他格式
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * 实现 compareTo() 方法，因为 DelayQueue 中的元素会根据过期时间进行排序。
     * 最先过期的元素放在队列的头部，过期时间最长的元素放在队列的尾部
     */
    @Override
    public int compareTo(Delayed o) {
        return Ints.saturatedCast(this.startTime - ((DelayObject) o).startTime);
    }
    @Override
    public String toString() {
        return "DelayObject{" +
                "data='" + data + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
