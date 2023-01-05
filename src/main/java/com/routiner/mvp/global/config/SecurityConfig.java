package com.routiner.mvp.global.config;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 세션 사용 안함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable();

        // 모든 요청이 해당 필터를 거친다. CORS 정책을 벗어날 수 있음
        // Controller 위에 @CrossOrigin 어노테이션은 인증이 필요없는 요청만 CORS 피할 수 있게 됨.
        // 따라서 아래처럼, corsFilter 를 별도로 만들어 추가해주는 것이 좋다.
        http.addFilter(corsFilter);

        http.authorizeRequests()
                .antMatchers("/api/v1/members/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();


    }
}
