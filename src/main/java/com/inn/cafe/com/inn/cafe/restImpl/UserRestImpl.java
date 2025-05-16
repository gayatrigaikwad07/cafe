package com.inn.cafe.com.inn.cafe.restImpl;

import com.inn.cafe.com.inn.cafe.Service.UserService;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper;
import com.inn.cafe.com.inn.cafe.rest.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.inn.cafe.com.inn.cafe.constents.UserConstents.SOMETHING_WENT_WRONG;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;
    private Map<String, String> requestMap;
    private String UserConstents;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        this.requestMap = requestMap;
        try{
            return userService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();

            return UserUtils.getResponseEntity(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);

        }
        catch (Exception ex){
            ex.printStackTrace ();

        }
        return UserUtils.getResponseEntity(SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        return null;
    }

//    @Override
//    public ResponseEntity<String> update(Map<String, String> requestMap) {
//        return null;
//    }


    @Override
   public ResponseEntity<String> update(Map<String, String> requestMap) {
       try{
            return userService.update(requestMap);

        }
       catch (Exception ex){
           ex.printStackTrace ();        }
        return UserUtils.getResponseEntity(UserConstents, HttpStatus.INTERNAL_SERVER_ERROR);
   }

//    @Override
//    public ResponseEntity<String> checkToken() {
//        try{
//            return  userService.checkToken();
//
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return UserUtils.getResponseEntity(UserConstents, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @Override
    public ResponseEntity<String> checkToken(){
        return UserUtils.getResponseEntity ("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            return UserService.changePassword(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace ();
        }
        return UserUtils.getResponseEntity (UserConstents,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
