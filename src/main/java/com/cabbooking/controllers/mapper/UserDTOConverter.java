package com.cabbooking.controllers.mapper;

import com.cabbooking.domain.User;
import com.cabbooking.dto.UserDTO;
import com.cabbooking.dto.requests.AddUserRequest;
import com.cabbooking.dto.responses.AddUserResponse;

public class UserDTOConverter {
    public static User convertAddUserRequestToUser(AddUserRequest addUserRequest){
        return User.builder()
                .name(addUserRequest.getName())
                .age(addUserRequest.getAge())
                .gender(addUserRequest.getGender())
                .build();

    }
    public static User convertUserDTOToUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .build();
    }
    public static AddUserResponse convertUserToAddUserResponse(User user){
        AddUserResponse addUserResponse = new AddUserResponse();
        addUserResponse.setId(user.getId());
        addUserResponse.setName(user.getName());
        addUserResponse.setAge(user.getAge());
        addUserResponse.setGender(user.getGender());
        return addUserResponse;
    }
    public static UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAge(user.getAge());
        userDTO.setGender(user.getGender());
        return userDTO;
    }
}
