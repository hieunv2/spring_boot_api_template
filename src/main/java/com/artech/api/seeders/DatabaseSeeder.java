package com.artech.api.seeders;

import com.artech.api.entity.Users;
import com.artech.api.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder {

    private Logger logger = Logger.getLogger(DatabaseSeeder.class);
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseSeeder(
            UserRepository userRepository,
            JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
//        seedUsersTable();
    }




    private void seedUsersTable() {
        String sql = "SELECT username FROM user U WHERE U.username = \"hieunv\" LIMIT 1";
        List<Users> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(u.size() <= 0) {
            Users user = new Users();
            user.setUserName("hieunv");
            user.setEmail("test@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("test123"));
            userRepository.save(user);
            logger.info("Users Seeded");
        } else {
            logger.trace("Users Seeding Not Required");
        }
    }



}