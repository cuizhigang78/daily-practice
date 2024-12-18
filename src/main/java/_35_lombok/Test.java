package _35_lombok;

import _23_spring.A;
import _99_common.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("张三", 18));
        users.add(new User("张三", 18));
        users.forEach(System.out::println);

        System.out.println("去重后的用户信息：");

        // User类中没有重写equals和hashCode方法，所以这里去重是不生效的
        // 但是如果User类使用@Data注解，那么会自动生成equals和hashCode方法，这里去重就会生效
        users.stream().distinct().forEach(System.out::println);
    }
}
