package _32_base64;

import java.util.Arrays;
import java.util.Base64;

public class Base64Test {

    public static void main(String[] args) {

        byte[] bytes = new byte[] {-1, 1, 2, 3};

        System.out.println(Arrays.toString(bytes));
        String s = new String(bytes);
        System.out.println("s = " + s);
        byte[] bytes1 = s.getBytes();
        System.out.println(Arrays.equals(bytes, bytes1));

        String encode = encode(bytes);
        System.out.println(encode);
        byte[] bytes2 = decode(encode);
        System.out.println(Arrays.equals(bytes, bytes2));

    }

    private static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] decode(String s) {
        return Base64.getDecoder().decode(s);
    }
}
