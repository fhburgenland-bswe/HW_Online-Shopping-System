package org.lecture.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String password;
    private String shippingAddress;

    public User(String name, String email, String password, String shippingAddress) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.shippingAddress = shippingAddress;
    }
}
