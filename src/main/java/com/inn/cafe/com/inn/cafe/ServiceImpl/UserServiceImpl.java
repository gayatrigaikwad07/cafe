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

import com.google.common.base.Strings;
import com.inn.cafe.com.inn.cafe.JWT.CustomerUserDetailService;
import com.inn.cafe.com.inn.cafe.JWT.JwtFilter;
import com.inn.cafe.com.inn.cafe.JWT.JwtUtil;
import com.inn.cafe.com.inn.cafe.POJO.NewUser;
import com.inn.cafe.com.inn.cafe.Service.UserService;
import com.inn.cafe.com.inn.cafe.Utils.EmailUtils;
import com.inn.cafe.com.inn.cafe.Utils.UserUtils;
import com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper;
import com.inn.cafe.com.inn.cafe.constents.UserConstents;
import com.inn.cafe.com.inn.cafe.dao.UserDao;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CustomerUserDetailService customerUserDetailsService;


    @Autowired
    JwtFilter jwtFilter;

    @Getter
    private com.inn.cafe.com.inn.cafe.POJO.NewUser userDetails;



    @Autowired
    AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    private UserDao userRepository;

    @Autowired
    EmailUtils emailUtils;

    private String NewUser;



// Only for debugging; remove in production



    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
//        log.info("Inside login");
        log.info ("Inside login controller with request: {}", requestMap);

        String email = requestMap.get ("email");
        String password = requestMap.get ("password");

        log.info ("Email: {}", email);
        log.info ("Password: {}", password);


//        try {
//            email = requestMap.get ("email");
//            password = requestMap.get ("password");
//
//            Authentication auth = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(email, password)
//            );
//
//            if (auth.isAuthenticated()) {
//                UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
//
//                if (userDetails.isEnabled()) {
//                    String jwt = jwtUtil.generateToken(userDetails);
//                    return ResponseEntity.ok(jwt);
//                } else {
//                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                            .body("User account is inactive.");
//                }
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body("Invalid credentials.");
//            }
//        } catch (Exception e) {
//            log.error("Login error: ", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("An error occurred during login: " + e.getMessage());
//        }
//    }


        //@Override
        return null;
    }

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public ResponseEntity<String> log(Map<String, String> requestMap) {
        return null;
    }

//    @Override
//    public ResponseEntity<String> update(Map<String, String> requestMap) {
//        try{
//            if(jwtFilter.isAdmin ()){
//                Optional<NewUser> optional =  userDao.findAllById(Integer.parseInt (requestMap.get ("id")));
//                if(!optional.isEmpty()){
//                    UserDao.updateStatus(requestMap.get ("status"), Integer.parseInt (requestMap.get ("id")));
//                    sendMailToAllAdmin(requestMap.get ("status"), UserDao.getAllAdmin());
//
//                    //UserUtils.getResponseEntity("");
//                    return UserUtils.getResponseEntity ("User Status Updated Successfully",HttpStatus.OK);
//                }
//                else{
//                    UserUtils.getResponseEntity ("User Entity Present",HttpStatus.OK);
//                }
//
//            }
//            else {
//                //return UserUtils.getResponseEntity (UserUtils.WENT_SOMETHING_WRONG,HttpStatus.UNAUTHORIZED);
//                return UserUtils.getResponseEntity(UserConstents.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace ();
//        };
//        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private void sendMailToAllAdmin(String status, List<String> allAdmin) {
    }

    //@Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try{
            //return UserService.getAllUser();
            if(jwtFilter.isAdmin ()){
                return new ResponseEntity<> (userDao.getAllUser (),HttpStatus.OK);

            }
            else{
                return new ResponseEntity<> (new ArrayList<> (),HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            log.error ("{}", ex);
        }
        return new ResponseEntity<List<UserWrapper>> (new ArrayList<> (), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        return null;
    }

    private  void sendMailToAllAdmin(String status,String email,List<String>allAdmin){
        allAdmin.remove (jwtFilter.getCurrentUser());
        if(status != null && status.equalsIgnoreCase ("true")){
            emailUtils.sendSimpleMessage (jwtFilter.getCurrentUser (), "Account Apprroved", "NewUser :-" + NewUser  + "\n is approved by \nADMIN" + jwtFilter.getCurrentUser(),allAdmin());
        }
        else{
            emailUtils.sendSimpleMessage (jwtFilter.getCurrentUser (), "Account Disable", "NewUser :-" + NewUser  + "\n is approved by \nADMIN" + jwtFilter.getCurrentUser(),allAdmin());


        }

    }

    private List<String> allAdmin() {
        return null;
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return UserUtils.getResponseEntity ("true", HttpStatus.OK);
    }

    //@Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            NewUser newUserObj = userDao.findByEmail (jwtFilter.getCurrentUser ());
            if(!newUserObj.equals (null)){
                if(newUserObj.getPassword ().equals (requestMap.get ("oldPassword"))){
                    newUserObj.setPassword(requestMap.get("newPassword"));
                    userDao.save (newUserObj);
                    return UserUtils.getResponseEntity ("Password Updated Successfully ",HttpStatus.OK);

                }
                return UserUtils.getResponseEntity ("Incorrect", HttpStatus.BAD_REQUEST);

            }
            return UserUtils.getResponseEntity(UserConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

        }
        catch (Exception ex){
            ex.printStackTrace ();
        }
        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @Override
//    public ResponseEntity<String> update(Map<String, String> requestMap) {
//        return null;
//    }

    //@Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap){
        try{
            NewUser newUser = userDao.findByEmail (requestMap.get("email"));
            if(!Object.isNull(newUser) && !Strings.isNullOrEmpty (newUser.getEmail ())){
                emailUtils.forgotMail (newUser.getEmail (), "Credential by cafe Management", newUser.getPassword ());

                return UserUtils.getResponseEntity ("Check your mail :",HttpStatus.OK);

            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return UserUtils.getResponseEntity (UserConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
