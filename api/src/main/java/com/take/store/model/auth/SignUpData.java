package com.take.store.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpData {
    @Size(min = 5, max = 20)
    private String username;
    @Email
    @Size(min = 6, max = 50)
    private String email;
    @Size(min = 6, max = 20)
    private String password;
}
