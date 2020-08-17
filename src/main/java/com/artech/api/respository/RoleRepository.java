package com.artech.api.respository;

import com.artech.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByUsersUserName(String username);

    Role findByName(String name);
}
