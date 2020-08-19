package com.artech.api.respository;

import com.artech.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Integer> {
    Optional<Users> findByUserName(String username);
}
