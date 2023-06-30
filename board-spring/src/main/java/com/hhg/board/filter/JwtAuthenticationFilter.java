package com.hhg.board.filter;

import com.hhg.board.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //Request 들어오면 Request Header의 Authorization(권한) 필드의 Bearer 토큰을 가져온다.
    //토큰을 검증하고 검증 결과를 SecurityContext에 추가한다.
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);

            if (token != null && !token.equalsIgnoreCase("null")) {
                //토큰 검증해서 payload의 userEmail을 가져온다.
                String userEmail = tokenProvider.validate(token);

                //SecurityContext에 추가할 객체
                AbstractAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userEmail, null, AuthorityUtils.NO_AUTHORITIES);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //SecurityContext에 AbstractAuthenticationToken 객체를 추가해서 해당 스레드가
                //지속적으로 인증 정보를 가져올 수 있도록 한다.
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        //필터 체인 이어가게끔
        filterChain.doFilter(request, response);
    }

    //Reqeust Header에서 Authorization 필드의 Bearer Token 가져옴
    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        //Bearer 다음인 7번째 인덱스부터

        return null;
    }
}
