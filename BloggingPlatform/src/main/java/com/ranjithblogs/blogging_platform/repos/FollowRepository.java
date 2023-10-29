package com.ranjithblogs.blogging_platform.repos;

import com.ranjithblogs.blogging_platform.model.Follow;
import com.ranjithblogs.blogging_platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User targetUser, User follower);
}
