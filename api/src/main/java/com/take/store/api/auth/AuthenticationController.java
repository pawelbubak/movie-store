package com.take.store.api.auth;

import com.take.store.config.security.utils.JwtUtils;
import com.take.store.model.auth.SignInData;
import com.take.store.model.auth.SignUpData;
import com.take.store.model.auth.Token;
import com.take.store.model.user.User;
import com.take.store.service.AuthenticationService;
import com.take.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("signIn")
    public Token authenticateUser(@RequestBody @Valid SignInData data) {
        Authentication authentication = authenticationService.authenticate(
                data.getUsername(), data.getPassword());
        User userDetails = (User) authentication.getPrincipal();
        return Token.builder()
                .accessToken(jwtUtils.generateToken(authentication))
                .userId(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();
    }

    @PostMapping("signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid SignUpData data) {
        userService.registerUser(data.getUsername(), data.getEmail(), passwordEncoder.encode(data.getPassword()));
    }
}
