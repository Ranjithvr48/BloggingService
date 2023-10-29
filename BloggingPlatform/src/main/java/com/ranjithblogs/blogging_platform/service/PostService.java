package com.ranjithblogs.blogging_platform.service;

import com.ranjithblogs.blogging_platform.model.Post;
import com.ranjithblogs.blogging_platform.model.User;
import com.ranjithblogs.blogging_platform.repos.PostRepository;


import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    @Autowired
    PostRepository postRepo;

    public String createPost(Post post) {
        post.setDateCreated(LocalDateTime.now());
        postRepo.save(post);
        return "Post uploaded!!!!";
    }

    public String removePost(Long postId, User user) {

        Post post  = postRepo.findById(postId).orElse(null);
        if(post != null && post.getPostOwner().equals(user))
        {
            postRepo.deleteById(postId);
            return "Removed successfully";
        }
        else if (post == null)
        {
            return "Post to be deleted does not exist";
        }
        else{
            return "Un-Authorized delete detected....Not allowed";
        }
    }

    public boolean validatePost(Post Post) {
        return (Post!=null && postRepo.existsById(Post.getPostId()));
    }


    public Post getPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    public List<Post> getAllPosts(Integer pageNumber, Integer pageSize) {
        Pageable p= PageRequest.of(pageNumber,pageSize);
        Page<Post>PagePost=this.postRepo.findAll(p);
                List<Post>content =PagePost.getContent();
        return content;
    }
}
