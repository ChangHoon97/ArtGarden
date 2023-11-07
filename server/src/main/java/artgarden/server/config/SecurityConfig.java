//package artgarden.server.config;
//
//import artgarden.server.entity.Role;
//import artgarden.server.service.OAuthService;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.web.DefaultSecurityFilterChain;
//
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    private final OAuthService oAuthService;
//
//    public SecurityConfig(OAuthService oAuthService) {
//        this.oAuthService = oAuthService;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers("/").permitAll()
//                        .requestMatchers("/css/**").permitAll()
//                        .requestMatchers("/images/**").permitAll()
//                        .requestMatchers("/js/**").permitAll()
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
//                        .anyRequest().authenticated()
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/")
//                )
//                .oauth2Login(oauth2Login -> oauth2Login
//                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
//                                .userService(oAuthService)
//                        )
//                );
//    }
//
//}
//
