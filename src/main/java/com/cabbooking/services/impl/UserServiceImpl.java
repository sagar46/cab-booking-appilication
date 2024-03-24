package com.cabbooking.services.impl;

import com.cabbooking.domain.User;
import com.cabbooking.repositories.UserRepository;
import com.cabbooking.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        log.debug("UserServiceImpl.addUser call started...");
        User savedUser = userRepository.addUser(user);
        log.debug("UserServiceImpl.addUser call completed...");
        return savedUser;
    }
}
