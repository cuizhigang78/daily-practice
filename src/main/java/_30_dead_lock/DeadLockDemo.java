package _30_dead_lock;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockA + "\t试图持有" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有" + lockB + "\t试图持有" + lockA);
            }
        }
    }
}
/**
 * 什么是死锁？
 *     死锁是指两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象。若无外力干涉，那它们将都无法推进下去。
 * 如果系统资源充足，进程的资源请求都能得到满足，死锁出现的可能性就很低，反之就会因争夺有限的资源而陷入死锁。
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
        /**
         * linux ps -ef|grep xxxx ls -l
         *
         * windows jps -l
         */
    }
}

















