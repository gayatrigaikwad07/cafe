//package com.inn.cafe.com.inn.cafe.ServiceImpl;
//
//import com.inn.cafe.com.inn.cafe.POJO.NewUser;
//import com.inn.cafe.com.inn.cafe.Service.UserService;
//import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
//import com.inn.cafe.com.inn.cafe.constents.UserConstents;
//import com.inn.cafe.com.inn.cafe.dao.UserDao;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.Objects;
//
////import static sun.security.ssl.SSLLogger.info;
//
//@Service
//@Slf4j
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserDao userDao ;
//
//
//    @Override
//
//    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
//
//        Process log = null;
//        //log.info("Inside Signup {}", requestMap);
//        //final var info = log.info ("Inside Signup {}", requestMap);
//        final var info = log.info ();
//        try {
//            if(validateSignMap (requestMap)){
//                NewUser newUser = userDao.findByEmail(requestMap.get("email"));
//                if(Objects.isNull (newUser)){
//                    userDao.save(getUserFromMap (requestMap));
//                    return UserUtils.getResponseEntity ("Susscessfully Register",HttpStatus.OK);
//
//                }
//                else{
//                    return UserUtils.getResponseEntity ("Email Already Exist", HttpStatus.BAD_REQUEST);
//                }
//
//            }
//            else {
//                return UserUtils.getResponseEntity (UserConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception ex){
//            ex.printStackTrace ();
//        }
//        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//
//    }
//
//    private boolean validateSignMap(Map<String,String>requestMap){
//        if(requestMap.containsKey ("name") && requestMap.containsKey ("contactNumber") && requestMap.containsKey ("email") && requestMap.containsKey ("password")){
//            return true;
//
//        }
//        return false;
//
//    }
//    public NewUser getUserFromMap(Map<String, String> requestMap) {
//        NewUser newUser = new NewUser();
//        newUser.setName(requestMap.get("name"));
//        newUser.setContactNumber(requestMap.get("contactNumber"));
//        newUser.setEmail(requestMap.get("email"));
//        newUser.setPassword(requestMap.get("password"));
//        newUser.setStatus(requestMap.get("status"));
//        newUser.setRole(requestMap.get("role"));
//        return newUser;
//    }
//
//
//}


package com.inn.cafe.com.inn.cafe.ServiceImpl;

import com.inn.cafe.com.inn.cafe.POJO.NewUser;
import com.inn.cafe.com.inn.cafe.Service.UserService;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.constents.UserConstents;
import com.inn.cafe.com.inn.cafe.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            log.info("Inside Signup {}", requestMap);

            if (validateSignMap(requestMap)) {
                NewUser newUser = userDao.findByEmail(requestMap.get("email"));
                if (Objects.isNull(newUser)) {
                    userDao.save(getUserFromMap(requestMap));
                    return UserUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return UserUtils.getResponseEntity("Email Already Exists", HttpStatus.BAD_REQUEST);
                }
            } else {
                return UserUtils.getResponseEntity(UserConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.error("Error during signup", ex);
            return UserUtils.getResponseEntity(UserConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateSignMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private NewUser getUserFromMap(Map<String, String> requestMap) {
        NewUser newUser = new NewUser();
        newUser.setName(requestMap.get("name"));
        newUser.setContactNumber(requestMap.get("contactNumber"));
        newUser.setEmail(requestMap.get("email"));
        newUser.setPassword(requestMap.get("password"));
        newUser.setStatus(requestMap.get("status"));
        newUser.setRole(requestMap.get("role"));
        return newUser;
    }
}
