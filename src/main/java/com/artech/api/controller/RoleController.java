package com.artech.api.controller;

import com.artech.api.reponse.APIResponse;
import com.artech.api.reponse.RoleDTO;
import com.artech.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping("/username/{username}")
    public ResponseEntity<APIResponse<List<RoleDTO>>> findUserByUserName(@PathVariable("username") String username){
        List<RoleDTO> roleDTOList = roleService.findByUsersUserName(username);
        APIResponse<List<RoleDTO>> response = new APIResponse<>();
        response.setMessage("thanh cong");
        response.setStatus(HttpStatus.OK.value());
        response.setData(roleDTOList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
