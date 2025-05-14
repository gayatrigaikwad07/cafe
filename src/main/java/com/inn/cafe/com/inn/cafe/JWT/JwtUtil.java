//package com.inn.cafe.com.inn.cafe.JWT;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import static java.lang.System.currentTimeMillis;
//
//@Service
//public class jwtUtil {
//    private String secret ="btech";
//
//    public static String extractUserName(String token){
//        return extractClaims (token,Claims::getSubject);
//    }
//
//    public Date extractexpiration(String token){
//        return extractClaims(token,Claims::getExpiration);
//    }
//
//    public <T> T extractClaims(String token, Function<Claims, T> claimResolver){
//        final Claims claims  = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    public Claims extractAllClaims(String token){
//        return Jwts.parser().setSigningKey (secret).parseClaimsJwt (token).getBody();
//    }
//    private boolean isTokenExpired(String token){
//        return extractexpiration(token).before (new Date ());
//    }
//
//    public String generateToken(String username, String role){
//        Map<String, Object> claims = new HashMap<> ();
//        claims.put ("role", role);
//        creteToken (claims,username);
//    }
//
//    private String creteToken(Map<String, Object> claims, String subject){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(currentTimeMillis()))
//                        .setExpiration(new Date(currentTimeMillis() + 1000*60*60*10))
//                .signWith(SignatureAlgorithm.ES256,secret).compact ();
//    }
//    private boolean validateToken(String token, UserDetails userDetails){
//        final String username = extractUserName (token);
//        return (username.equals (userDetails.getUsername ()) && !isTokenExpired (token));
//    }
//}



package com.inn.cafe.com.inn.cafe.JWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private final String secret = "btech";

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .claim("roles", claims)  // Adding roles to the JWT
                .setIssuedAt(new Date())
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
//    public String generateToken(UserDetails userDetails) {
//        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
//        String roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
//
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles", roles)  // Adding roles to the JWT
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .compact();
//    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername ()) && !isTokenExpired(token);
    }

    public String generateToken(UserDetails userDetails) {
        return null;
    }
}
