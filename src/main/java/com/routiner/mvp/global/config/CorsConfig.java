package com.routiner.mvp.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);    // 서버에서 응답할 때, json 을 자바스크립트에서 처리할 수 있도록 설정
        configuration.addAllowedOrigin("*");        // 모든 IP 의 응답을 허용
        configuration.addAllowedHeader("*");        // 모든 Header 에 응답 허용
        configuration.addAllowedMethod("*");        // 모든 GET, POST, PUT 등 HTTP Method 를 허용

        source.registerCorsConfiguration("/api/**", configuration);
        return new CorsFilter(source);
    }
}
