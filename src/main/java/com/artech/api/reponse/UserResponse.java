package com.artech.api.reponse;

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
public class UserResponse {
    private int userId;
    private String username;
    private String fullName;
    private String email;
    private int status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<RoleDTO> roles;
}
