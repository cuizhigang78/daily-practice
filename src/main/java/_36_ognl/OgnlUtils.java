package _36_ognl;

import java.lang.reflect.Field;

public class OgnlUtils {

    public static Object getValue(String expression, Object root) throws NoSuchFieldException, IllegalAccessException {
        Field field = root.getClass().getDeclaredField(expression);
        field.setAccessible(true);
        return field.get(root);
    }

    public static void setValue(String expression, Object root, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = root.getClass().getDeclaredField(expression);
        field.setAccessible(true);
        field.set(root, value);
    }
}