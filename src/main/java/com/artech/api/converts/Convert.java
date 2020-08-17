package com.artech.api.converts;


import com.artech.api.entity.Role;
import com.artech.api.entity.Users;
import com.artech.api.reponse.RoleDTO;
import com.artech.api.reponse.UserResponse;
import com.artech.api.request.UserRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Convert {

    private Convert(){}

    public UserResponse toResponse(Users user){
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        return userResponse;
    }

    public List<UserResponse> toResponse(Iterable<Users> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (Users user : users) responses.add(toResponse(user));
        return responses;
    }

    public Users toUser(UserRequest userRequest, List<Role> roles, Users oldUser){
        Users user = new Users();
        if(oldUser != null){
            user = oldUser;
        }
        BeanUtils.copyProperties(userRequest,user);
        user.setRoles(roles);
        return user;
    }
}
