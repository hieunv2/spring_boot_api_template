package com.artech.api.controller;

import com.artech.api.converts.UserConvert;
import com.artech.api.entity.Users;
import com.artech.api.exceptions.NotFoundException;
import com.artech.api.reponse.APIResponse;
import com.artech.api.reponse.ProductDTO;
import com.artech.api.reponse.UserResponse;
import com.artech.api.request.UserRequest;
import com.artech.api.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/users")
@RestController
public class UserController extends BaseController<UsersService, UserConvert> {

    @Autowired
    private UsersService userService;

    @GetMapping
    public ResponseEntity getAllCate(){
        Iterable<Users> users = userService.findAll();
        return response(convert.toResponse(users));
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id){
        Optional<Users> user = userService.findById(id);
        return response(convert.toResponse(user.get()));

    }

    public ResponseEntity create(@RequestBody UserRequest request) {
        Optional<Users> opt = service.create(request);

        if (!opt.isPresent()) throw new NotFoundException("Cannot add new User");

        return response(convert.toResponse(opt.get()));
    }
//
//    @PostMapping
//    public ResponseEntity<APIResponse<UserResponse>> create(@RequestBody UserRequest user){
//        UserResponse newUser = userService.save(user);
//        APIResponse<UserResponse> userResponse = new APIResponse<>();
//        userResponse.setStatus(HttpStatus.OK.value());
//        userResponse.setMessage("Success");
//        userResponse.setData(newUser);
//        return new ResponseEntity<>(userResponse,HttpStatus.OK);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<APIResponse<UserResponse>> update(@RequestBody UserRequest user, @PathVariable("id") int id){
//        UserResponse userUpdate = userService.update(user,id);
//        APIResponse<UserResponse> apiResponse = new APIResponse<>();
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setMessage("Success");
//        apiResponse.setData(userUpdate);
//        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
//
//    }
}
