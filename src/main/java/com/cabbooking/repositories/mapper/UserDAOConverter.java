package com.cabbooking.repositories.mapper;

import com.cabbooking.dao.UserDAO;
import com.cabbooking.domain.User;

public class UserDAOConverter {
    public static UserDAO convertUserToUserDao(User user) {
        return UserDAO.builder()
                .name(user.getName())
                .age(user.getAge())
                .gender(user.getGender())
                .build();
    }

    public static User convertUserDaoToUser(UserDAO userDAO) {
        return User.builder()
                .id(userDAO.getId())
                .name(userDAO.getName())
                .gender(userDAO.getGender())
                .age(userDAO.getAge())
                .build();
    }
}
