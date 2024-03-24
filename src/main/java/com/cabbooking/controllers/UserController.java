package com.cabbooking.controllers;

import com.cabbooking.controllers.mapper.UserDTOConverter;
import com.cabbooking.domain.User;
import com.cabbooking.dto.Response;
import com.cabbooking.dto.requests.AddUserRequest;
import com.cabbooking.dto.responses.AddUserResponse;
import com.cabbooking.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Value("${user.success.add-user}")
    private String userAddedMsg;
    @PostMapping("/")
    public Response<AddUserResponse> addUser(@RequestBody AddUserRequest addUserRequest) {
        log.debug("UserController.addUser call started...");
        User user = userServiceImpl.addUser(UserDTOConverter.convertAddUserRequestToUser(addUserRequest));
        String userAddedSuccessMsg = MessageFormat.format(userAddedMsg,user.getName());
        log.debug("UserController.addUser call completed...");
        return Response.<AddUserResponse>builder()
                .data(UserDTOConverter.convertUserToAddUserResponse(user))
                .message(userAddedSuccessMsg)
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
