package org.unlogged.springwebfluxdemo.security.model.reqrespBodies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ReqLogin {
    private String email;
    private String password;
}
