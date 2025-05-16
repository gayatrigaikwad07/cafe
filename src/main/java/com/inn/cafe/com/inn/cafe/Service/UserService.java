package com.inn.cafe.com.inn.cafe.Service;

import com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {


    ResponseEntity<String> login(Map<String,String>RequestMap);

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> log(Map<String, String> requestMap);
    //ResponseEntity<List<UserWrapper>> getAllUser();
    static ResponseEntity<List<UserWrapper>> getAllUser() {
        return null;
    }
    ResponseEntity<String> checkToken();


    ResponseEntity<String> update(Map<String, String> requestMap);

    static ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        return null;
    }

     ///ResponseEntity<String> changePassword(Map<String, String> requestMap);
}
