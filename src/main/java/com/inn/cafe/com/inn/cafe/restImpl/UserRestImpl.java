package com.inn.cafe.com.inn.cafe.restImpl;

import com.inn.cafe.com.inn.cafe.Service.UserService;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.constents.UserConstents;
import com.inn.cafe.com.inn.cafe.rest.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();

            return UserUtils.getResponseEntity(UserConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
