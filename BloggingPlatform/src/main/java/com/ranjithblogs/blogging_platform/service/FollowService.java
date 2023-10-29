package com.ranjithblogs.blogging_platform.service;

import com.ranjithblogs.blogging_platform.model.Follow;
import com.ranjithblogs.blogging_platform.model.User;
import com.ranjithblogs.blogging_platform.repos.FollowRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class FollowService {

    @Autowired
    FollowRepository followRepo;

    public void startFollowing(Follow follow, User follower) {

        follow.setCurrentUserFollower(follower);
        followRepo.save(follow);
    }

    public boolean isFollowAllowed(User targetUser, User follower) {

        List<Follow> followList =  followRepo.findByCurrentUserAndCurrentUserFollower(targetUser,follower);

        return followList!=null && followList.isEmpty() && !targetUser.equals(follower);
    }

    public Follow findFollow(Long followId) {
        return followRepo.findById(followId).orElse(null);
    }

    public void unfollow(Follow follow) {
        followRepo.delete(follow);
    }

}
