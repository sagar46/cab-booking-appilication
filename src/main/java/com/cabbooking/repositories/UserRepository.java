package com.cabbooking.repositories;

import com.cabbooking.dao.UserDAO;
import com.cabbooking.domain.User;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.UserNotFoundException;
import com.cabbooking.repositories.mapper.UserDAOConverter;
import com.cabbooking.store.UserStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository {
    private final UserStore userStore;



    public User addUser(User user) {
        log.debug("UserRepository.adduser call started...");
        UserDAO savedUser = saveUser(UserDAOConverter.convertUserToUserDao(user));
        log.debug("UserRepository.addUser call completed...");
        return UserDAOConverter.convertUserDaoToUser(savedUser);
    }

    public UserDAO saveUser(UserDAO userDAO) {
        return userStore.addUserInDb(userDAO);
    }

    public User getUserByUsername(String username) {
        log.debug("UserRepository.getUserByUsername call started...");
        UserDAO user = userStore.getUserByUsername(username);
        log.debug("UserRepository.getUserByUsername call completed...");
        return UserDAOConverter.convertUserDaoToUser(user);
    }

    public List<User> getAllUsers() {
        log.debug("UserRepository.getAllUsers call started...");
        List<User> users = getUsers().stream().map(UserDAOConverter::convertUserDaoToUser).collect(Collectors.toList());
        log.debug("UserRepository.getAllUsers call completed...");
        return users;
    }

    public List<UserDAO> getUsers() {
        return userStore.getAllUser();
    }
}
