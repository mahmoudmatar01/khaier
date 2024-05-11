package com.example.khaier.security;

import com.example.khaier.dto.response.UserInfoResponseDto;
import com.example.khaier.entity.User;
import com.example.khaier.enums.Gender;
import com.example.khaier.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtTokenUtils {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetails) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setIssuer("app-service")
                .setExpiration(calendar.getTime())
                .claim("created", Calendar.getInstance().getTime())
                .claim("userId", ((User) userDetails).getUserId())
                .claim("userRole", ((User) userDetails).getUserRole().toString())
                .claim("userGender", ((User) userDetails).getUserGender().toString())
                .claim("userEmail", ((User) userDetails).getEmail())
                .claim("userPhone", ((User) userDetails).getPhone())
                .claim("userImageUrl", ((User) userDetails).getUserImageUrl())
                .signWith(SECRET_KEY)
                .compact();
    }

    public String getUserNameFromToken(String token){
        Claims claims=getClaims(token);
        return claims.getSubject();
    }
    public String getUserEmailFromToken(String token){
        Claims claims=getClaims(token);
        return claims.get("userEmail", String.class);
    }


    public String getTokenIdFromToken(String token){
        Claims claims=getClaims(token);
        return claims.getId();
    }

    public boolean isTokenValid(String token, UserDetails user){
        String userName=getUserNameFromToken(token);
        return userName.equalsIgnoreCase(user.getUsername())&&!isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        Date expiration=getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }



    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public UserInfoResponseDto extractUserFromToken(String token) {
        Claims claims = getClaims(token);
        Long userId = claims.get("userId", Long.class);
        String username = claims.getSubject();
        String userEmail = claims.get("userEmail", String.class);
        Role userRole = Role.valueOf(claims.get("userRole", String.class));
        Gender userGender = Gender.valueOf(claims.get("userGender", String.class));
        String phone = claims.get("userPhone", String.class);
        String imageUrl = claims.get("userImageUrl", String.class);

        return UserInfoResponseDto.builder()
                .userId(userId)
                .name(username)
                .email(userEmail)
                .imageUrl(imageUrl)
                .role(userRole)
                .gender(userGender)
                .phone(phone)
                .build();
    }
}

