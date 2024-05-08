package com.miniproject.mnoutilityservice.endpoint;


import api.mnoutilityservice.miniproject.com.users.*;
import lombok.extern.slf4j.Slf4j;
import com.miniproject.mnoutilityservice.entity.User;
import com.miniproject.mnoutilityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Slf4j
@RequiredArgsConstructor
@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://com.miniproject.mnoutilityservice.api/users";


    final private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest request) {
        AddUserResponse response = new AddUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        User user = new User();

        BeanUtils.copyProperties(request.getUserInfo(), user);
        userService.addUser(user);
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("User added successfully");
        response.setServiceStatus(serviceStatus);
        response.setUserInfo(request.getUserInfo());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) throws Exception {
        GetUserByIdResponse response = new GetUserByIdResponse();

        UserInfo userInfo = new UserInfo();

        User user = userService.getUserById(request.getUserId());
        BeanUtils.copyProperties(user, userInfo);
        response.setUserId(user.getUserId());
        response.setUserInfo(userInfo);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) throws Exception {
        UpdateUserResponse response = new UpdateUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        User user = userService.getUserById(request.getUserId());

        BeanUtils.copyProperties(request.getUserInfo(), user);
        user.setUserId(request.getUserId());
        userService.updateUser(user);
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("User updated successfully");
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
        DeleteUserResponse response = new DeleteUserResponse();
        ServiceStatus serviceStatus = new ServiceStatus();


        userService.deleteUser(request.getUserId());
        serviceStatus.setStatus("Success");
        serviceStatus.setMessage("User removed successfully");
        response.setServiceStatus(serviceStatus);

        return response;

    }


}
