package com.take.store.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class SignInData {
    @Size(min = 5, max = 20)
    private String username;
    @Size(min = 6, max = 20)
    private String password;
}
