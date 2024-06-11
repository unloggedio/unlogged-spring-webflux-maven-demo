package org.unlogged.springwebfluxdemo.security.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.unlogged.springwebfluxdemo.security.service.JWTService;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

public class AuthManager implements ReactiveAuthenticationManager {

    final JWTService jwtService;
    final ReactiveUserDetailsService users;

    public AuthManager(JWTService jwtService, ReactiveUserDetailsService users) {
        this.jwtService = jwtService;
        this.users = users;

    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(
                authentication
        )
                .cast(BearerToken.class)
                .flatMap(auth -> {
                    String userName = jwtService.getUserName(auth.getCredentials());
                    Mono<UserDetails> foundUser = users.findByUsername(userName).defaultIfEmpty(new UserDetails() {
                        @Override
                        public Collection<? extends GrantedAuthority> getAuthorities() {
                            return null;
                        }

                        @Override
                        public String getPassword() {
                            return null;
                        }

                        @Override
                        public String getUsername() {
                            return null;
                        }

                        @Override
                        public boolean isAccountNonExpired() {
                            return false;
                        }

                        @Override
                        public boolean isAccountNonLocked() {
                            return false;
                        }

                        @Override
                        public boolean isCredentialsNonExpired() {
                            return false;
                        }

                        @Override
                        public boolean isEnabled() {
                            return false;
                        }
                    });
                    Mono<Authentication> authenticatedUser = foundUser.flatMap(u -> {
                        if(u.getUsername() == null) {
                            Mono.error(new IllegalArgumentException("User not found in auth manager"));
                        }
                        if(jwtService.validate(u, auth.getCredentials())) {
                            return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), u.getAuthorities()));
                        }
                        Mono.error(new IllegalArgumentException("Invalid/Expired token"));
                        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword(), u.getAuthorities()));
                    });
                    return authenticatedUser;
                });
    }
}
