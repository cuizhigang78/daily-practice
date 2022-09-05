package _31_Javap;

public class JavapTest {

    private static final int len = 779;

    public int add(int x) {
        try {
            // 若运行时检测到 x = 0,那么 jvm会自动抛出异常，(可以理解成由jvm自己负责 athrow 指令调用)
            x = 100 / x;
        } catch (Exception e) {
            x = 100;
        }
        return x;
    }
    // # 编译
    // javac JavapTest.java
    // # 使用javap 查看 add 方法被编译后的机器指令
    // javap -v[erbose] JavapTest.class
    //
    // public int add(int);
    //    descriptor: (I)I
    //    flags: ACC_PUBLIC
    //    Code:
    //      stack=2, locals=3, args_size=2
    //         0: bipush 100   // 加载参数100
    //         2: iload_1 // 将一个int型变量推至栈顶
    //         3: idiv // 相除
    //         4: istore_1 // 除的结果值压入本地变量
    //         5: goto          11    // 跳转到指令：11
    //         8: astore_2 // 将引用类型值压入本地变量
    //         9: bipush 100   // 将单字节常量推送栈顶<这里与数值100有关，可以尝试修改100后的编译结果：iconst、bipush、ldc>
    //        10: istore_1 // 将int类型值压入本地变量
    //        11: iload_1 // int 型变量推栈顶
    //        12: ireturn // 返回
    //      // 注意看 from 和 to 以及 targer，然后对照着去看上述指令
    //      Exception table:
    //         from to target type
    //             0     5     8   Class java/lang/Exception
    //      LineNumberTable:
    //        line 6: 0
    //        line 9: 5
    //        line 7: 8
    //        line 8: 9
    //        line 10: 11
    //      StackMapTable: number_of_entries = 2
    //        frame_type = 72 /* same_locals_1_stack_item */
    //          stack = [ class java/lang/Exception ]
    //        frame_type = 2 /* same */
}
