package com.ranjithblogs.blogging_platform.repos;

import com.ranjithblogs.blogging_platform.model.AuthenticationToken;
import com.ranjithblogs.blogging_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    AuthenticationToken findFirstByUser(User user);

    AuthenticationToken findFirstByTokenValue(String authTokenValue);
}
