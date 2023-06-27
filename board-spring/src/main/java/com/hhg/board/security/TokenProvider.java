package com.hhg.board.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    //JWT 생성 검증 키 생성
    private static final String SECURITY_KEY = "jwtseckey!@";

    //JWT 생성 메소드
    public String create (String userEmail) {
        //만료날짜 => 현재 날짜 + 1시간
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        //JWT 생성
        return Jwts.builder()
                //암호화에 사용할 알고리즘, 키
                .signWith(SignatureAlgorithm.HS512, SECURITY_KEY)
                //JWT 제목, 생성일, 만료일
                .setSubject(userEmail).setIssuedAt(new Date()).setExpiration(exprTime)
                //생성
                .compact();
    }

    //JWT 검증
    public String validate (String token) {
        //매개변수로 받은 토큰을 키를 사용해서 복호화
        Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
        //디코딩(복호화)된 토큰의 payload에서 제목을 가져옴
        return claims.getSubject();
    }
}
