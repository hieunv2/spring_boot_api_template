package com.artech.api.controller;

import com.artech.api.converts.Convert;
import com.artech.api.entity.Users;
import com.artech.api.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class UserController extends BaseController<Users> {

    private final UsersService userService;

    @Autowired
    private Convert convert;

    @Override
    protected void setModel() {
        this.model = new Users();
    }

    UserController(UsersService userService) {
        super(userService);
        this.userService = userService;
    }



//    @PutMapping("/{id}")
//    public ResponseEntity update(@RequestBody UserRequest user, @PathVariable("id") int id){
//        Optional<Users> opt = userService.update(user,id);
//
//        if (!opt.isPresent()) throw new NotFoundException("Cannot add new User");
//
//        return response(convert.toResponse(opt.get()));
//    }
//
//    @PostMapping
//    public ResponseEntity create(@RequestBody UserRequest request) {
//        Optional<Users> opt = userService.create(request);
//
//        if (!opt.isPresent()) throw new NotFoundException("Cannot add new User");
//
//        return response(convert.toResponse(opt.get()));
//    }


}
