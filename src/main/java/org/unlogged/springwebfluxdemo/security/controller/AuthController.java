package org.unlogged.springwebfluxdemo.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.security.model.reqrespBodies.ReqLogin;
import org.unlogged.springwebfluxdemo.security.model.reqrespModel.ReqRespModel;
import org.unlogged.springwebfluxdemo.security.service.JWTService;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    final ReactiveUserDetailsService users;
    final JWTService jwtService;
    final PasswordEncoder passwordEncoder;

    public AuthController(ReactiveUserDetailsService users, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/auth")
    public Mono<ResponseEntity<ReqRespModel<String>>> auth() {
        return Mono.just(
                ResponseEntity.ok(new ReqRespModel<>(
                        "You are authenticated",
                        ""
                ))
        );
    }

//    @PostMapping("/login")
//    public Mono<ResponseEntity<ReqRespModel<String>>> login(@RequestBody ReqLogin user) {
//        System.out.println(user.getEmail() +"checko");
//        System.out.println(user.getPassword() +"checkopass");
//        System.out.println(users.findByUsername(user.getEmail()) + "regevs123");
//       Mono<UserDetails> foundUser = users
//               .findByUsername(user.getEmail())
//               .defaultIfEmpty(null);
//
//       return foundUser.flatMap(u -> {
//           if (u != null) {
//               if(u.getPassword() == user.getPassword()) {
//                   return Mono.just(
//                           ResponseEntity.ok(
//                           new ReqRespModel<>(jwtService.generate(u.getUsername()), "Success")
//                        )
//                   );
//               }
//               return Mono.just(
//                       ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReqRespModel<>("","Invalid Credentials"))
//               );
//           }
//           return Mono.just(
//                   ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReqRespModel<>("","User not found"))
//           );
//       });
//    }

    @PostMapping("/login")
    public Mono<ResponseEntity<ReqRespModel<String>>> login(@RequestBody ReqLogin user) {
        return users.findByUsername(user.getEmail())
                .flatMap(foundUser -> {
                    if (foundUser != null && passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
                        String token = jwtService.generate(foundUser.getUsername());
                        return Mono.just(ResponseEntity.ok(new ReqRespModel<>(token, "Success")));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(new ReqRespModel<>("", "Invalid Credentials")));
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(404)
                        .body(new ReqRespModel<>("", "User not found"))));
    }
}

