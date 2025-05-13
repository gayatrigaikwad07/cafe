package com.inn.cafe.com.inn.cafe.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserUtils {

    public UserUtils(){

    }
    public static ResponseEntity<String> getResponseEntity(String ResponseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String> ("{\"message\":\""+ResponseMessage+ "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
