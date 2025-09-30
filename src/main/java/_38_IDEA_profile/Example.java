package _38_IDEA_profile;

public class Example {
    public static void main(String[] args) {
        methodA();
        methodA();
        methodA();
    }

    public static void methodA() {
        System.out.println("methodA");
        // 模拟耗时操作
        for (int i = 0; i < 10000000; i++) {
            Math.random();
        }
        methodB();
    }

    public static void methodB() {
        System.out.println("methodB");
        // 模拟耗时操作
        for (int i = 0; i < 10000000; i++) {
            Math.random();
        }
        methodC();
    }

    public static void methodC() {
        System.out.println("methodC");
        // 模拟耗时操作
        for (int i = 0; i < 10000000; i++) {
            Math.random();
        }
    }
}