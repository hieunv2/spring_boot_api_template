package com.artech.api.services;

import com.artech.api.respository.RoleRepository;
import com.artech.api.entity.Role;
import com.artech.api.reponse.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<RoleDTO> findByUsersUserName(String userName) {
        List<RoleDTO> roleResponses = new ArrayList<>();
        List<Role> roleList = roleRepository.findAllByUsersUserName(userName);
        for (Role role : roleList ){
            RoleDTO roleResponse = modelMapper.map(role,RoleDTO.class);
            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }
}
