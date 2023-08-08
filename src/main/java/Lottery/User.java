package Lottery;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private String name;
    private String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
