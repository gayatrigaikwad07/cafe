package com.inn.cafe.com.inn.cafe.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public abstract class WebSecurityConfigurerAdapter {
    //@Override
    protected abstract void configure(AuthenticationManagerBuilder auth) throws Exception;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return null;
    }

    protected abstract void configure(HttpSecurity http) throws Exception;
}
