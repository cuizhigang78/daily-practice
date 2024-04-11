package _04_pass_value;

import _99_common.entity.Person;

public class TestPassValue {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("xxx");
    }
    public void changeValue21(Person person) {
        person = new Person("def");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {

        TestPassValue test = new TestPassValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age---" + age);  // 20

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName---" + person.getPersonName());  //xxx

        person = new Person("abc");
        test.changeValue21(person);
        System.out.println("personName---" + person.getPersonName());  //abc

        String str = "abc";
        test.changeValue3(str);
        System.out.println("str---" + str);  // abc
    }
}
