package com.ranjithblogs.blogging_platform.repos;

import com.ranjithblogs.blogging_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public  interface UserRepository extends JpaRepository<User, Long> {
       User findFirstByUserEmail(String signInEmail);

}
