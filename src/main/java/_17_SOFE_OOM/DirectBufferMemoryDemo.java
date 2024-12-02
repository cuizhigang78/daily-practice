package _17_SOFE_OOM;

import java.nio.ByteBuffer;

/**
 * create by 携山超 on 2020/4/25
 *
 * java.lang.OutOfMemoryError:Direct buffer memory
 *
 */
public class DirectBufferMemoryDemo {
    // 写NIO程序时经常会用到ByteBuffer来读取或者写入数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/O方式，
    // 它可以使用Native函数直接分配堆外内存，然后通过一个存储在Java堆中的DirectByteBuffer对象作为这块内存的引用进行操作。
    // 这样在一些场景中能显著提高性能，因为避免了在Java堆和Native堆中来回复制数据。

    // ByteBuffer.allocate(capability)，分配JVM内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢；
    // ByteBuffer.acclcateDirect(capability).分配OS本地内存，不属于GC管辖范围，不需要拷贝，速度相对较快。

    // 如果不断地分配本地内存，而不分配JVM内存，本地内存已满，而JVM却不会触发GC，DirectByteBuffer对象无法被回收。
    // 这时再尝试分配本地内存，就会出现OutOfMemoryError，导致程序崩溃。
    public static void main(String[] args) {
        // JDK9 以后不再支持
        // System.out.println("直接内存大小为：" + sun.misc.VM.maxDirectMemory() / (1024 * 1024) + "M");
        // 3627M 约等于机器内存的1/4

        // 手动将直接内存调整为5M，再创建一个大小为6M的ByteBuffer对象
        // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        ByteBuffer.allocateDirect(6 * 1024 * 1024);
        // Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    }
}
