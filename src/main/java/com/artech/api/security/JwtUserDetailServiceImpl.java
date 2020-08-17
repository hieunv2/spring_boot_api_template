package com.artech.api.security;

import com.artech.api.entity.Users;
import com.artech.api.reponse.RoleDTO;
import com.artech.api.reponse.UserResponse;
import com.artech.api.services.RoleService;
import com.artech.api.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UsersService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        Users users = userService.findUserByUserName(userName);
        List<GrantedAuthority> grantList = new ArrayList<>();
        List<RoleDTO> roleNames = this.roleService.findByUsersUserName(userName);
        if (roleNames != null) {
            for (RoleDTO role : roleNames) {
                grantList.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        return new User(users.getUserName(), users.getPassword(), grantList);
    }
}
