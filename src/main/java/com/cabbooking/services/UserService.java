package com.cabbooking.services;

import com.cabbooking.domain.User;
import com.cabbooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final UserRepository userRepository;

    public User addUser(User user) {
        log.debug("UserService.addUser call started...");
        User savedUser = userRepository.addUser(user);
        log.debug("UserService.addUser call completed...");
        return savedUser;
    }
}
