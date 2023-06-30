package com.hhg.board.config;

import com.hhg.board.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                //Application에서 설정을 미리 해둠(CORS정책)
                cors().and()
                //csrf 대책 비활성화
                .csrf().disable()
                //Basic 인증 비활성화 => Bearer 토큰을 사용하므로
                .httpBasic().disable()
                //세션 기반 인증 사용 여부 X
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //'/','/api/auth' 모듈에 대해서는 인증을 따로 하지 않고 모두 허용
                .authorizeRequests().antMatchers("/", "/api/auth/**").permitAll()
                //나머지 요청에 대해서는 모두 인증된 사용자만 이용 가능하게 함
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
