package com.artech.api.services.impl;

import com.artech.api.converts.Convert;
import com.artech.api.entity.Role;
import com.artech.api.entity.Users;
import com.artech.api.exceptions.NotFoundException;
import com.artech.api.request.UserRequest;
import com.artech.api.respository.RoleRepository;
import com.artech.api.respository.UserRepository;
import com.artech.api.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserRepository, Users, Integer> implements UsersService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Convert convert;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Users> create(UserRequest userRequest) {
        if (userRequest == null) throw new NotFoundException("Request for Creating user is invalid");
        List<Role> roles = new ArrayList<>();
        if(userRequest.getRoleIds() != null && userRequest.getRoleIds().length> 0){
            List<Integer> roleIds = Arrays.stream(userRequest.getRoleIds()).boxed().collect(Collectors.toList());
            roles = roleRepository.findAllById(roleIds);
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Users user =  convert.toUser(userRequest,roles,null);
        return save(user);
    }

    @Override
    public Optional<Users> update(UserRequest userRequest, int id) {
        Optional<Users> oldUser = findById(id);
        if (!oldUser.isPresent()) throw new NotFoundException("User not found");

        if (userRequest == null) throw new NotFoundException("Request for Updating user is invalid");
        List<Role> roles = new ArrayList<>();
        if(userRequest.getRoleIds() != null && userRequest.getRoleIds().length> 0){
            List<Integer> roleIds = Arrays.stream(userRequest.getRoleIds()).boxed().collect(Collectors.toList());
            roles = roleRepository.findAllById(roleIds);
        }
        if(userRequest.getPassword() != null){
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        Users user =  convert.toUser(userRequest,roles, oldUser.get());
        return save(oldUser.get());
    }

    @Override
    public Users findUserByUserName(String name) {
        return repo.findByUserName(name);
    }
}