package com.ranjithblogs.blogging_platform.controller;

import com.ranjithblogs.blogging_platform.model.*;
import com.ranjithblogs.blogging_platform.service.AuthenticationTokenService;
import com.ranjithblogs.blogging_platform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationTokenService authenticationService;
    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String sigInUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String sigOutUser(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }
    @PostMapping("post")
    public String createPost(@RequestBody Post post, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.createPost(post,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("post")
    public String removePost(@RequestParam Long postId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.removePost(postId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }



    @PostMapping("comment")
    public String addComment(@RequestBody Comment comment, @RequestParam String commenterEmail, @RequestParam String commenterToken)
    {
        if(authenticationService.authenticate(commenterEmail,commenterToken)) {
            return userService.addComment(comment,commenterEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }


    @DeleteMapping("comment")
    public String removeComment(@RequestParam Long commentId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.removeComment(commentId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
    @PostMapping("follow")
    public String followUser(@RequestBody Follow follow, @RequestParam String followerEmail, @RequestParam String followerToken)
    {
        if(authenticationService.authenticate(followerEmail,followerToken)) {
            return userService.followUser(follow,followerEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @DeleteMapping("unfollow/target/{followId}")
    public String unFollowUser(@PathVariable Long followId, @RequestParam String followerEmail, @RequestParam String followerToken)
    {
        if(authenticationService.authenticate(followerEmail,followerToken)) {
            return userService.unFollowUser(followId,followerEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
    @GetMapping("post")
    public List <Post> getPost(@RequestParam Integer PageNumber,@RequestParam Integer PageSize) {
   List<Post> allpost=this.userService.getAllPosts(PageNumber,PageSize);
            return allpost;

    }
    @PutMapping("post/{postId}")
    public String updatePost(@PathVariable Long postId, @RequestBody Post updatedPost, @RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            return userService.updatePost(postId, updatedPost, email);
        } else {
            throw new RuntimeException("Not an Authenticated user activity!!!");
        }
    }
    @GetMapping("comment/{postId}")
    public List<Comment> getCommentById(@PathVariable Long postId,@RequestParam Integer PageNumber,@RequestParam Integer PageSize) {
        List<Comment> allcomment=this.userService.getCommentById(postId,PageNumber,PageSize);
        return allcomment;


    }
    @PutMapping("comment/{commentId}")
    public String updateComment(@PathVariable Long commentId, @RequestBody Comment updatedComment, @RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            return userService.updateComment(commentId, updatedComment, email);
        } else {
            throw new RuntimeException("Not an Authenticated user activity!!!");
        }
    }


}
