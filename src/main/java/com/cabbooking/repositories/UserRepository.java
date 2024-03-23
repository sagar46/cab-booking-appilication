package com.cabbooking.repositories;

import com.cabbooking.dao.UserDAO;
import com.cabbooking.domain.User;
import com.cabbooking.repositories.mapper.UserDAOConverter;
import com.cabbooking.store.UserStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository {
    private final UserStore userStore;

    public User addUser(User user){
        log.debug("UserRepository.adduser call started...");
        UserDAO userDAO = UserDAOConverter.convertUserToUserDao(user);
        UserDAO savedUser =  saveUser(userDAO);
        log.debug("UserRepository.addUser call completed...");
        return UserDAOConverter.convertUserDaoToUser(savedUser);
    }
    public UserDAO saveUser(UserDAO userDAO){
        return userStore.addUserInDb(userDAO);
    }
    public List<UserDAO> getAllUser(){
        return userStore.getAllUser();
    }
}
