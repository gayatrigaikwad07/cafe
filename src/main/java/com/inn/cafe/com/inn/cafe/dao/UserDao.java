package com.inn.cafe.com.inn.cafe.dao;

import com.inn.cafe.com.inn.cafe.POJO.NewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<NewUser,Integer> {

    NewUser findByEmail(@Param ("email")String email);
}
