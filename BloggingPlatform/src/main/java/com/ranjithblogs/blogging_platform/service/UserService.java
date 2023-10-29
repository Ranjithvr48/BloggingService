package com.ranjithblogs.blogging_platform.service;

import com.ranjithblogs.blogging_platform.model.*;
import com.ranjithblogs.blogging_platform.repos.UserRepository;
import com.ranjithblogs.blogging_platform.service.emailUtility.EmailHandler;
import com.ranjithblogs.blogging_platform.service.hashingUtility.PasswordEncrypter;


import com.ranjithblogs.blogging_platform.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationTokenService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());


            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }


    public String signInUser(SignInInput signInInput) {


        String signtusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signtusMessage = "Invalid email";
            return signtusMessage;


        }


        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if(existingUser == null)
        {
            signtusMessage = "Email not registered!!!";
            return signtusMessage;

        }


        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {

                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signtusMessage = "Invalid credentials!!!";
                return signtusMessage;
            }
        }
        catch(Exception e)
        {
            signtusMessage = "Internal error occurred during sign in";
            return signtusMessage;
        }

    }


    public String sigOutUser(String email) {

        User user = userRepo.findFirstByUserEmail(email);
        AuthenticationToken token = authenticationService.findFirstByUser(user);
        authenticationService.removeToken(token);
        return "User Signed out successfully";
    }


    public String createPost(Post post, String email) {

        User postOwner = userRepo.findFirstByUserEmail(email);
        post.setPostOwner(postOwner);
        return postService.createPost(post);
    }

    public String removePost(Long postId,String email) {

        User user = userRepo.findFirstByUserEmail(email);
        return postService.removePost(postId,user);
    }

    public String addComment(Comment comment,String commenterEmail) {

        boolean postValid = postService.validatePost(comment.getBlogPost());
        if(postValid) {
            User commenter = userRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenter(commenter);
            return commentService.addComment(comment);
        }
        else {
            return "Cannot comment on Invalid Post!!";
        }
    }

    boolean authorizeCommentRemover(String email,Comment comment)
    {
        String  commentOwnerEmail = comment.getCommenter().getUserEmail();
        String  postOwnerEmail  = comment.getBlogPost().getPostOwner().getUserEmail();

        return postOwnerEmail.equals(email) || commentOwnerEmail.equals(email);
    }

    public String removeComment(Long commentId, String email) {
        Comment comment  = commentService.findComment(commentId);
        if(comment!=null)
        {
            if(authorizeCommentRemover(email,comment))
            {
                commentService.removeComment(comment);
                return "comment deleted successfully";
            }
            else
            {
                return "Unauthorized delete detected...Not allowed!!!!";
            }

        }
        else
        {
            return "Invalid Comment";
        }
    }

    public String followUser(Follow follow, String followerEmail) {


        User followTargetUser = userRepo.findById(follow.getCurrentUser().getId()).orElse(null);

        User follower = userRepo.findFirstByUserEmail(followerEmail);

        if(followTargetUser!=null)
        {
            if(followService.isFollowAllowed(followTargetUser,follower))
            {
                followService.startFollowing(follow,follower);
                return follower.getUserName()  + " is now following " + followTargetUser.getUserName();
            }
            else {
                return follower.getUserName()  + " already follows " + followTargetUser.getUserName();
            }
        }
        else {
            return "User to be followed is Invalid!!!";
        }


    }

    private boolean authorizeUnfollow(String email, Follow follow) {

        String  targetEmail = follow.getCurrentUser().getUserEmail();
        String  followerEmail  = follow.getCurrentUserFollower().getUserEmail();

        return targetEmail.equals(email) || followerEmail.equals(email);
    }

    public String unFollowUser(Long followId, String followerEmail) {

        Follow follow  = followService.findFollow(followId);
        if(follow != null)
        {
            if(authorizeUnfollow(followerEmail,follow))
            {
                followService.unfollow(follow);
                return follow.getCurrentUser().getUserName() + "not followed by " + followerEmail;
            }
            else
            {
                return "Unauthorized unfollow detected...Not allowed!!!!";
            }

        }
        else
        {
            return "Invalid follow mapping";
        }
    }

    public Post getPostById(Long postId, String email) {

        User user = userRepo.findFirstByUserEmail(email);
        Post post = postService.getPostById(postId);

        if (post != null && post.getPostOwner().equals(user)) {
            return post;
        } else {

            throw new RuntimeException("You are not authorized to view this post!");
        }
    }
    public String updatePost(Long postId, Post updatedPost, String email) {

        User user = userRepo.findFirstByUserEmail(email);
        Post post = postService.getPostById(postId);

        if (post != null && post.getPostOwner().equals(user)) {

            post.setPostContent(updatedPost.getPostContent());
            post.setPostLocation(updatedPost.getPostLocation());


            postService.createPost(post);

            return "Post updated successfully!";
        } else {

            throw new RuntimeException("You are not authorized to update this post!");
        }
    }

    public List<Comment> getCommentById(Long postId,Integer PageNumber,Integer PageSize) {


      List<Comment> comment = commentService.getAllCommentsForPost(postId,PageNumber,PageSize);


            return comment;

    }

    public String updateComment(Long commentId, Comment updatedComment, String email) {

        User user = userRepo.findFirstByUserEmail(email);
        Comment comment = commentService.findComment(commentId);

        if (comment != null && comment.getCommenter().equals(user)) {

            comment.setCommentBody(updatedComment.getCommentBody());


            commentService.addComment(comment);

            return "Comment updated successfully!";
        } else {

            throw new RuntimeException("You are not authorized to update this comment!");
        }
    }



    public List<Post> getAllPosts(Integer pageNumber, Integer pageSize) {
        return postService.getAllPosts(pageNumber,pageSize);
    }
}
