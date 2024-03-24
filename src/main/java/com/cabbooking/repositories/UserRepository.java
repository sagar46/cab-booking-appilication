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

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository {
    private final UserStore userStore;

    @Value("${user.error.already-exist}")
    private String userAlreadyExistMsg;
    @Value("${user.error.not-found}")
    private String userNotFoundMsg;
    public User addUser(User user) {
        log.debug("UserRepository.adduser call started...");
        UserDAO userDAO = UserDAOConverter.convertUserToUserDao(user);
        UserDAO savedUser = saveUser(userDAO);
        log.debug("UserRepository.addUser call completed...");
        return UserDAOConverter.convertUserDaoToUser(savedUser);
    }

    public UserDAO saveUser(UserDAO userDAO) {
        List<UserDAO> userDAOS = getAllUser();
        userDAOS.forEach(userExist -> {
            if (Objects.equals(userExist.getName(), userDAO.getName())) {
                throw new CabBookingException(userAlreadyExistMsg);
            }
        });
        return userStore.addUserInDb(userDAO);
    }

    public User getUserByUsername(String username) {
        List<UserDAO> allUsers = getAllUser();
        AtomicReference<UserDAO> user = new AtomicReference<>(UserDAO.builder().build());
        allUsers.forEach(userDAO -> {
            if (userDAO.getName().equals(username)) {
                user.set(userDAO);
            }
        });
        if (Objects.isNull(user.get())) {
            throw new UserNotFoundException(userNotFoundMsg);
        }
        return UserDAOConverter.convertUserDaoToUser(user.get());
    }

    public List<UserDAO> getAllUser() {
        return userStore.getAllUser();
    }
}
