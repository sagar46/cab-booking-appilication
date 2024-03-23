package com.cabbooking.store;

import com.cabbooking.dao.UserDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserStore {
    private final List<UserDAO> userStore;

    public UserStore(List<UserDAO> userStore) {
        this.userStore = userStore;
    }

    public UserDAO addUserInDb(UserDAO userDAO) {
        if (!userStore.isEmpty()) {
            userDAO.setId(userStore.get(userStore.size() - 1).getId() + 1);
        } else {
            userDAO.setId(1);
        }
        userStore.add(userDAO);
        return userDAO;
    }

    public List<UserDAO> getAllUser() {
        return userStore;
    }
}
