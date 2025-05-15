package com.inn.cafe.com.inn.cafe.JWT;

import com.inn.cafe.com.inn.cafe.POJO.NewUser;
import com.inn.cafe.com.inn.cafe.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    private NewUser userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info ("Inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmail (username);
        if (userDetail != null) {
            return userDetail;
        } else {
            throw new UsernameNotFoundException ("User not found with username: " + username);
        }
    }
}

//    public NewUser getUserDetail() {
//        return userDetail;
//    }
//}

