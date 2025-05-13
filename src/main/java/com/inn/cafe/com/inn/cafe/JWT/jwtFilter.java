package com.inn.cafe.com.inn.cafe.JWT;//package com.inn.cafe.com.inn.cafe.JWT;
//
//import com.inn.cafe.com.inn.cafe.POJO.NewUser;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.aspectj.weaver.patterns.IToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.preauth.websphere.WebSpherePreAuthenticatedWebAuthenticationDetailsSource;
//import org.springframework.security.web.header.Header;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.security.Security;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private jwtUtil JwtUtil;
//    @Autowired
//    private CustomerUserDetailService service;
//    private ServletResponse httpServletResonse;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if(httpServletRequest.getServletPath ().matches ("/user/login| /use/forgotPassword |/user/signup")){
//            filterChain.doFilter (httpServletRequest, httpServletResonse);
//        }
//        else{
//            String authorizationHeader = httpServletRequest.getHeader ("Authorization");
//            String Tokens = null;
//
//
//            if(authorizationHeader != null && authorizationHeader.startsWith("bearer")){
//                token = authorizationHeader.substring (7);
//                Username = jwtUtil.extractUserName (token);
//                claims =  jwtUtil.extractAllClaims;(token)
//            }
//
//            if(userName !=null && SecurityContextHolder.getContext ().getAuthentication ()==null){
//                UserDetails userDetails = service.loadUserByUsername (userName);
//                if(jwtUtil.validateToken(token,userDetails)){
//                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken (userDetails,null, userDetails.getAuthorities ());
//                    usernamePasswordAuthenticationToken.setDetails (new WebSpherePreAuthenticatedWebAuthenticationDetailsSource ().buildDetails (httpServletRequest));
//                    SecurityContextHolder.getContext ().setAuthentication (usernamePasswordAuthenticationToken);
//
//                }
//            }
//            filterChain.doFilter (httpServletRequest,httpServletResonse);
//        }
//
//
//
//    }
//    public boolean isAdmin(){
//        return "admin".equalsIgnoreCase ((String) claims.get("role"));
//    }
//    public boolean isUser(){
//        return "user".equalsIgnoreCase ((String) claims.get("role"));
//    }
//    public String getCurrentUser(){
//        return userName;
//    }
//}




import com.inn.cafe.com.inn.cafe.JWT.CustomerUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import io.jsonwebtoken.Claims;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private CustomerUserDetailService service;

    private Claims claims = null;
    private String userName = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //if (request.getServletPath().matches("/user/signup")) {
            ///user/login|/user/forgotPassword|
        if (request.getServletPath().matches("/user/signup")) {//(login|signup|forgotPassword)"

            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUserName(token);
            claims = jwtUtil.extractAllClaims(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(userName);
            if (jwtUtil.validateToken (token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    public boolean isUser() {
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser() {
        return userName;
    }
}
