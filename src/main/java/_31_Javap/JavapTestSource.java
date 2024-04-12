package _31_Javap;

public class JavapTestSource {

    private static final int len = 779;

    public int add(int x) {
        try {
            x = 100 / x;
        } catch (Exception e) {
            x = 100;
        }
        return x;
    }
}
