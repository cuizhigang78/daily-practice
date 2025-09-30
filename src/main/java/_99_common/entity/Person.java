package _99_common.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class Person implements Serializable {

    private Integer id;
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }
}