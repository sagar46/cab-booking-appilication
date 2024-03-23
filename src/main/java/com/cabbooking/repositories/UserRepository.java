package com.cabbooking.repositories;

import com.cabbooking.dao.UserDAO;
import com.cabbooking.domain.User;
import com.cabbooking.repositories.mapper.UserDAOConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRepository {

    public User addUser(User user){
        log.debug("UserRepository.adduser call started...");
        UserDAO userDAO = UserDAOConverter.convertUserToUserDao(user);
        UserDAO savedUser =  saveUser(userDAO);
        log.debug("UserRepository.addUser call completed...");
        return UserDAOConverter.convertUserDaoToUser(savedUser);
    }
    public UserDAO saveUser(UserDAO userDAO){
        return userDAO;
    }
}
