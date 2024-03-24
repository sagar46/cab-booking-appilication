package com.cabbooking.store;

import com.cabbooking.dao.UserDAO;
import com.cabbooking.domain.User;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.exception.DuplicateUserOrDriverException;
import com.cabbooking.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {

    private int ID = 1;
    private final ConcurrentHashMap<String, UserDAO> userStore = new ConcurrentHashMap<>();
    @Value("${user.error.already-exist}")
    private String userAlreadyExistMsg;
    @Value("${user.error.not-found}")
    private String userNotFoundMsg;

    public UserDAO addUserInDb(UserDAO userDAO) {
        UserDAO userExist = userStore.get(userDAO.getName());
        if (Objects.nonNull(userExist)){
            throw new DuplicateUserOrDriverException(userAlreadyExistMsg);
        }
        userDAO.setId(ID++);
        userStore.put(userDAO.getName(), userDAO);
        return userDAO;
    }
    public UserDAO getUserByUsername(String username){
        UserDAO user = userStore.get(username);
        if (Objects.isNull(user)){
            throw new UserNotFoundException(userNotFoundMsg);
        }
        return user;
    }

    public List<UserDAO> getAllUser() {
        List<UserDAO> users = new ArrayList<>();
       userStore.forEach((key,value)->{
           users.add(value);
       });
       return users;
    }
}
