package hello;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import hello.model.Admin;
import hello.model.UserAccount;

@Component
public class JwtGenerator {

//    public String generate(JwtUser jwtUser) {
//
//
//        Claims claims = Jwts.claims()
//                .setSubject(jwtUser.getUserName());
//        claims.put("username", String.valueOf(jwtUser.getUserName()));
//        claims.put("password", jwtUser.getPassword());
//
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS512, "team_12")
//                .compact();
//    }
	
	public String generateForUser(UserAccount userAccountForJWT) {
        Claims claims = Jwts.claims()
                .setSubject(userAccountForJWT.getUsername());
        claims.put("username", String.valueOf(userAccountForJWT.getUsername()));
        claims.put("password", userAccountForJWT.getPassword());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "team_12")
                .compact();
    }
	
	public String generateForAdmin(Admin adminAccountForJWT) {
        Claims claims = Jwts.claims()
                .setSubject(adminAccountForJWT.getUsername());
        claims.put("username", String.valueOf(adminAccountForJWT.getUsername()));
        claims.put("password", adminAccountForJWT.getPassword());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "team_12")
                .compact();
    }
}
