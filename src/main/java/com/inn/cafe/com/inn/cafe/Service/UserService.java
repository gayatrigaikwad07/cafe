package com.inn.cafe.com.inn.cafe.Service;

import com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {


    ResponseEntity<String> login(Map<String,String>RequestMap);

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> log(Map<String, String> requestMap);


    //public ResponseEntity<List<UserWrapper>> getAllUser();

    static ResponseEntity<List<UserWrapper>> getAllUser() {
        return null;
    }
}
