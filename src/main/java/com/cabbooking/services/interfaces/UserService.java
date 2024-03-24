package com.cabbooking.services.interfaces;

import com.cabbooking.domain.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public List<User> getAllUsers();
}
