package com.artech.api.converts;


import com.artech.api.entity.Role;
import com.artech.api.reponse.RoleDTO;


public class RoleConvert {
    public  static RoleDTO convertRoleToDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getRoleId());
        roleDTO.setRoleName(role.getName());
        return roleDTO;
    }
}
