package _36_ognl;

public class OgnlUtilsTest {

    public static void main(String[] args) {
        try {
            // Example object
            Person person = new Person();
            person.setName("John");
            person.setAge(30);

            // Get property value
            String name = (String) OgnlUtils.getValue("name", person);
            System.out.println("Name: " + name);

            // Set property value
            OgnlUtils.setValue("name", person, "Jane");
            System.out.println("Updated Name: " + person.getName());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}