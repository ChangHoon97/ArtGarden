package artgarden.server.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
//                                .requestMatchers(new MvcRequestMatcher(introspector,"login/**")).permitAll()
                                .requestMatchers(new MvcRequestMatcher(introspector, "/")).permitAll()
//                                .requestMatchers("/posts/**", "/api/v1/posts/**").hasRole(Role.USER.name())
//                                .requestMatchers("/admins/**","/api/v1/admins/**").hasRole(Role.ADMIN.name())
                                .anyRequest().permitAll()
                );

        return http.build();
    }*/

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        // http://localhost:4200 에 대한 CORS 허용
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        config.setAllowedOrigins(Collections.singletonList("https://localhost:3000"));
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:3060"));
                        config.setAllowedOrigins(Collections.singletonList("https://localhost:3060"));
                        config.setAllowedOrigins(Collections.singletonList("https://artgarden.co.kr"));
                        config.setAllowedOrigins(Collections.singletonList("https://artgarden.site"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                // csrf 비활성화 (사이트 요청 위조) ---> csrf 토큰이 없어도 서버는 응답
                // 스프링에서는 csrf 기본은 활성화 (보안 목적) ---> csrf 토큰을 url에 포함해야 서버는 응답
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // json을 자바스크립트에서 처리할 수 있게 설정
        config.addExposedHeader("accessToken"); // 노출할 헤더 설정
        config.addExposedHeader("refreshToken"); // 노출할 헤더 설정
        config.addAllowedOriginPattern("*"); // 모든 ip의 응답을 허용
        config.addAllowedHeader("*"); // 모든 header의 응답을 허용
        config.addAllowedMethod("*"); // 모든 post, put 등의 메서드에 응답을 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);    // 모든 경로에 Cors설정
        return source;
    }

}

