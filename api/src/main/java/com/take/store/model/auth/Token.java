package com.take.store.model.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Token {
    private final String tokenType = "Bearer";
    private String accessToken;
    private long userId;
    private String username;
    private String email;
    private List<String> roles;
}

