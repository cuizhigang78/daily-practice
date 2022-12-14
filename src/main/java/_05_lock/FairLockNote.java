package _05_lock;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

/**
 * 公平锁：是指多个线程按照申请锁的顺序来获取锁。在并发环境下，每个线程在获取锁时会先查看此锁维护的等待队列，
 *     如果为空，或者当前线程是待队列的第一个，才占有锁，否则就加入到等待队列中，按FIFO的规则等待。
 *
 * 非公平锁：是指多个线程获取锁的顺序并不是按照申请锁的顺序，有可能后申请的线程比先申请的线程优先获取锁。
 *     在高并发的情况下，有可能会造成优先级反转或者饥饿现象。非公平锁比较粗鲁，上来就直接尝试占有锁，如果失败，
 *     再采用类似公平锁的方式。
 *
 *     并非包（juc）中的ReentrantLock的创建可以指定构造函数的boolean类型来得到公平锁或非公平锁，默认是公平锁。
 * 非公平锁的优点在于吞吐量比公平锁大。
 *     synchronized也是一种非公平锁。
 *
 */
public class FairLockNote {

    public static void main(String[] args) {
    }
}
