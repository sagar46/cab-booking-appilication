package com.cabbooking.utils;

import com.cabbooking.domain.User;
import com.cabbooking.enums.Gender;
import com.cabbooking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUserTestCases {
    private final UserRepository userRepository;

    public void addUserTestCases() {
        List<User> users = new ArrayList<>();
        users.add(createUser("Abhishek", 23, Gender.MALE));
        users.add(createUser("Rahul", 29, Gender.MALE));
        users.add(createUser("Nandini", 22, Gender.FEMALE));
        users.forEach(userRepository::addUser);
    }

    public User createUser(String name, int age, Gender gender) {
        return User.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .build();
    }

}
