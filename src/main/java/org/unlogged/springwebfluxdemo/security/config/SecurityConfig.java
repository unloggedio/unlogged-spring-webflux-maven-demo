package org.unlogged.springwebfluxdemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.unlogged.springwebfluxdemo.security.service.JWTService;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("Akshat")
                .password(passwordEncoder.encode("password"))
                .roles("USER").build();
        UserDetails user2 = User.builder()
                .username("Authorized Player 2")
                .password(passwordEncoder.encode("password2"))
                .roles("USER", "ADMIN").build();
        return new MapReactiveUserDetailsService(user);
    }
    @Bean
    public AuthManager authManager(JWTService jwtService, ReactiveUserDetailsService users) {
        return new AuthManager(jwtService, users);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthConverter jwtAuthConverter, AuthManager jwtAuthManager) {

        AuthenticationWebFilter jwtFilter = new AuthenticationWebFilter(jwtAuthManager);
        jwtFilter.setServerAuthenticationConverter(jwtAuthConverter);
        return http
                .authorizeExchange(auth -> {
                    auth.pathMatchers("/login").permitAll();
                    auth.anyExchange().authenticated();
                })
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
