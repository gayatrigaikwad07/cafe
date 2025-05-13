package com.inn.cafe.com.inn.cafe.JWT;//package com.inn.cafe.com.inn.cafe.JWT;//package com.inn.cafe.com.inn.cafe.JWT;
////
////import com.inn.cafe.com.inn.cafe.POJO.NewUser;
////import com.inn.cafe.com.inn.cafe.dao.UserDao;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////
////import java.util.ArrayList;
////
////public class CustomerUserDetailService implements UserDetailsService {
////
////    @Autowired
////    UserDao userDao;
////
////    private com.inn.cafe.com.inn.cafe.POJO.NewUser UserDetails;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
////        UserDetails = userDao.findByEmail (username);
////        if(!Object.isNull(UserDetails)){
////            return new NewUser(UserDetails.getEmail(),UserDetails.getPassword(), new ArrayList<>());
////        }
////        else{
////            throw new UsernameNotFoundException ("Use not found");
////        }
////    }
////    public com.inn.cafe.com.inn.cafe.POJO.NewUser getUserDetails(){
////        return UserDetails;
////    }
////
////}
//
////
////import com.inn.cafe.com.inn.cafe.POJO.NewUser;
////import com.inn.cafe.com.inn.cafe.dao.UserDao;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////
////import java.util.ArrayList;
////
////public class CustomerUserDetailService implements UserDetailsService {
////
////    private static final Logger log = LoggerFactory.getLogger(CustomerUserDetailService.class);
////
////    private final UserDao userDao;
////
////    @Autowired
////    public CustomerUserDetailService(UserDao userDao) {
////        this.userDao = userDao;
////    }
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        log.info("Inside loadUserByUsername method with username: {}", username);
////        UserDetails userDetails = (UserDetails) userDao.findByEmail(username);
////        if (userDetails != null) {
////            return new NewUser userDetails.getUsername(), userDetails.getPassword(), new ArrayList<> ();
////        }
////        throw new UsernameNotFoundException("User not found");
////    }
////}
//
//
//
//import com.inn.cafe.com.inn.cafe.POJO.NewUser;
//import com.inn.cafe.com.inn.cafe.dao.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class CustomerUserDetailService implements UserDetailsService {
//
//    private static final Logger log = LoggerFactory.getLogger(CustomerUserDetailService.class);
//
//    private final UserDao userDao;
//
//    @Autowired
//    public CustomerUserDetailService(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("Inside loadUserByUsername method with username: {}", username);
//        UserDetails userDetails = (UserDetails) userDao.findByEmail(username);
//        if (userDetails != null) {
//            return new NewUser (userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
//        }
//        throw new UsernameNotFoundException("User not found");
//    }
//}



import com.inn.cafe.com.inn.cafe.POJO.NewUser;
import com.inn.cafe.com.inn.cafe.dao.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Getter
    private NewUser userDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetails = userDao.findByEmail(username);
        if (userDetails == null) {

            throw new UsernameNotFoundException("User not found");
           // NewUser implements UserDetails
        }
        return (UserDetails) userDetails;
    }

}

