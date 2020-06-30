package com.take.store.service;

import com.take.store.model.exception.EmailAlreadyUsedException;
import com.take.store.model.exception.UsernameAlreadyExistsException;
import com.take.store.model.user.Role;
import com.take.store.model.user.User;
import com.take.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        } else if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyUsedException(email);
        }
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .role(Role.CUSTOMER)
                .enabled(true)
                .build();
        userRepository.save(user);
    }
}
