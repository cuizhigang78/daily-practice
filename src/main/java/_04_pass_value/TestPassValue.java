package _04_pass_value;

import _99_common.entity.Person;

/**
 * 所谓值传递，就是把实参的值，拷贝一份给形参
 *
 * 对于基本数据类型，拷贝的是值
 *
 * 对于引用类型，拷贝的是引用的地址，也就是说，形参和实参是指向同一个对象的两个引用，改变形参指向的对象，不会改变实参指向的对象
 */
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
