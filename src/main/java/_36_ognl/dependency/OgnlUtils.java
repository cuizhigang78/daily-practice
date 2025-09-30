package _36_ognl.dependency;

import _36_ognl.Person;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import java.util.Map;

/**
 * <dependency>
 *     <groupId>ognl</groupId>
 *     <artifactId>ognl</artifactId>
 *     <version>3.1</version>
 * </dependency>
 */
public class OgnlUtils {

    private static OgnlContext createContext(Map<String, Object> contextParams) {
        OgnlContext context = new OgnlContext();
        if (contextParams != null) {
            context.putAll(contextParams);
        }
        return context;
    }

    public static Object getValue(String expression, Object root) throws OgnlException {
        OgnlContext context = new OgnlContext();
        return Ognl.getValue(expression, context, root);
    }

    public static void setValue(String expression, Object root, Object value) throws OgnlException {
        OgnlContext context = new OgnlContext();
        Ognl.setValue(expression, context, root, value);
    }

    public static void main(String[] args) {
        try {
            // 示例对象
            Person person = new Person();
            person.setName("John");
            person.setAge(30);

            // 获取属性值
            String name = (String) OgnlUtils.getValue("name", person);
            System.out.println("Name: " + name);

            // 设置属性值
            OgnlUtils.setValue("name", person, "Jane");
            System.out.println("Updated Name: " + person.getName());

        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }
}