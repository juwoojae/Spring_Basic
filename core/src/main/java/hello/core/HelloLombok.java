package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter  //get와,set메서드들을 자동으로 에노테이션 프로세싱으로 만들어 준다
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sfsdfdas");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}
