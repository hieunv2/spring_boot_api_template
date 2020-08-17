package com.artech.api.request;

import com.artech.api.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private int status;
    private int[] roleIds;

    private List<Role> roles;
}
