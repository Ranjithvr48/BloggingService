package com.ranjithblogs.blogging_platform.service;

import com.ranjithblogs.blogging_platform.model.AuthenticationToken;
import com.ranjithblogs.blogging_platform.model.User;

import com.ranjithblogs.blogging_platform.repos.AuthenticationTokenRepository;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class AuthenticationTokenService {

    @Autowired
    AuthenticationTokenRepository authenticationRepo;

    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(AuthenticationToken authToken)
    {
        authenticationRepo.save(authToken);
    }

    public AuthenticationToken findFirstByUser(User user) {
        return authenticationRepo.findFirstByUser(user);
    }

    public void removeToken(AuthenticationToken token) {
        authenticationRepo.delete(token);
    }

}
