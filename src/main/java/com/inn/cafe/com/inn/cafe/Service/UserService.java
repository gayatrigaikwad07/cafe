package com.inn.cafe.com.inn.cafe.Service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String,String>RequestMap);
}
