package com.take.store.service;

import com.take.store.model.exception.AccountCredentialsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error("User account is locked.", e);
            throw new AccountExpiredException(username);
        } catch (BadCredentialsException e) {
            log.error("Login failed. Bad account credentials.");
            throw new AccountCredentialsException();
        }
    }
}
