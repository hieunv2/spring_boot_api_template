package com.artech.api.services;

import com.artech.api.request.UserRequest;
import com.artech.api.entity.Users;
import java.util.Optional;


public interface UsersService extends BaseService<Users, Integer>{

    Optional<Users> create(UserRequest request);

    Optional<Users> update(UserRequest request, int id);

    Users findUserByUserName(String name);

//
//     public List<Users> getAll() {
//         List<Users> users = userRepository.findAll();
//         return users;
//     }
//

//
//     public Users getUserById(int id){
//         return userRepository.findById(id);
//     }
//
//     public Users create(UserRequest userRequest) {
//         List<Role> roles = new ArrayList<>();
//         if(userRequest.getRoleIds() != null && userRequest.getRoleIds().length> 0){
//             List<Integer> roleIds = Arrays.stream(userRequest.getRoleIds()).boxed().collect(Collectors.toList());
//             roles = roleRepository.findAllById(roleIds);
//         }
//         userRequest.setRoles(roles);
//         Users userSave = new Users();
//         BeanUtils.copyProperties(userRequest,userSave);
//         Users newUser = userRepository.save(userSave);
//         UserResponse user = new UserResponse();
//         BeanUtils.copyProperties(userSave,user);
//         return user;
//     }

//    public UserResponse update(UserRequest userRequest, int id) {
//        List<Role> roles = new ArrayList<>();
//        Users userById = userRepository.findById(id);
//        if (userById != null) {
//            BeanUtils.copyProperties(userById,userRequest);
//            Users userSave = new Users();
//            BeanUtils.copyProperties(userRequest,userSave);
//            if(userRequest.getRoleIds() != null && userRequest.getRoleIds().length> 0){
//                List<Integer> roleIds = Arrays.stream(userRequest.getRoleIds()).boxed().collect(Collectors.toList());
//                roles = roleRepository.findAllById(roleIds);
//                userSave.setRoles(roles);
//            }
//            userRepository.
//            UserResponse user = new UserResponse();
//            BeanUtils.copyProperties(userSave,user);
//            return user;
//        } else {
//            throw new NotFoundException("Khong tim  thay ");
//        }
//    }

}
