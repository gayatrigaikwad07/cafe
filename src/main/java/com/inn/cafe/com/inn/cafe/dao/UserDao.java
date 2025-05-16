package com.inn.cafe.com.inn.cafe.dao;

import com.inn.cafe.com.inn.cafe.POJO.NewUser;
import com.inn.cafe.com.inn.cafe.Wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<NewUser,Integer> {

    NewUser findByEmail(@Param ("email")String email);

    List<UserWrapper> getAllUser();

    @Transactional
    @Modifying
    static void updateStatus(@Param("status") String status, @Param("id") Integer id) {
    }

    static List<String> getAllAdmin() {
        return null;
    }

//    static Integer updateStatus(@Param("status") String status, @Param("id") Integer id) {
//        return null;
//    }



    Optional<NewUser> findAllById(int id);

    //NewUser findByEmail(String email);
}
