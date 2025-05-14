package com.inn.cafe.com.inn.cafe.JWT;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private CustomerUserDetailService service;

    private Claims claims = null;
    private String userName = null;
    private Object SimpleGrantedAuthority;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.contains("/user/signup") || path.contains("/user/login") || path.contains("/user/forgotPassword") || path.contains("/user/get") ) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUserName(token);
            claims = jwtUtil.extractAllClaims(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = service.loadUserByUsername(userName);
            if (jwtUtil.validateToken(token, userDetails)) {
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
    public Authentication getAuthentication(String token) {
        String username = Jwts.parser()
                .setSigningKey(String.valueOf (SECRET_KEY))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();


        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority> ((Collection<? extends GrantedAuthority>) Arrays.asList (Jwts.parser ()
                        .setSigningKey (String.valueOf (SECRET_KEY))
                        .parseClaimsJws (token)
                        .getBody ()
                        .get ("roles", String.class).split (","))
                .stream ());

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

}
