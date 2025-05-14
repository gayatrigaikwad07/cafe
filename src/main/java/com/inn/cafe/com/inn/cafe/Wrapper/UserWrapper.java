package com.inn.cafe.com.inn.cafe.Wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWrapper {

    UserWrapper NewUser = new com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper(1, "nisha","nisha@gmail.com","95737","false");
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private String status;

    public UserWrapper(Integer id, String name,String email, String contactNumber, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}
