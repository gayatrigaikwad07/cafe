package com.inn.cafe.com.inn.cafe.JWT;//package com.inn.cafe.com.inn.cafe.JWT;
//
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//
//
//@Configuration
//@EnableWebSecurity
//
//public abstract class securityConfig extends WebSecurityConfiguration{
//
//    @Autowired
//    CustomerUserDetailService customerUserDetailService;
//
//    @Autowired
//    JwtFilter jwtFilter;
//
//    //@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(customerUserDetailService);
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance ();
//    }
//
//
//
//    @Bean(name = BeanIds.AUTHENTICATION_MANEGER)
//    @Override
//
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected  void configure(HttpSecurity http) throws Exception{
//        http cors().configurationSource(request -> new CorsConfiguration ().applyPermitDefaultValues ()).and().csrf().disable().authorizeRequest().antMatcher("/user/login", "user/signup", "user/forgotPassword")
//                .permitAll().anyRequest().authenticcated().and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//
//}




import com.inn.cafe.com.inn.cafe.JWT.CustomerUserDetailService;
import com.inn.cafe.com.inn.cafe.JWT.WebSecurityConfigurerAdapter;
import com.inn.cafe.com.inn.cafe.JWT.jwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerUserDetailService customerUserDetailService;

    @Autowired
    jwtFilter jwtFilter;

    //@Override
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers ("/signup", "/login").permitAll()  // Allow public access
                .anyRequest().authenticated()
    }

}
