package com.ErrWho.service.impl;

import com.ErrWho.entity.Post;
import com.ErrWho.repository.PostRepo;
import com.ErrWho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Override
    public Post save(Post post) {
        return postRepo.save(post);
    }

    @Override
    public List<Post> findAllByOrderByCreationTimeDesc() {
        return postRepo.findAllByOrderByCreationTimeDesc();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepo.findById(id);
    }

    public void updatePost(Long id, Post updatedPost) {
        Optional<Post> existingPost = findById(id);
        Post post = existingPost.get();
        post.setCategory(updatedPost.getCategory());
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());

        if (updatedPost.getImage() != null && updatedPost.getImage().length > 0) {
            post.setImage(updatedPost.getImage());
        }
        postRepo.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public Post getLatestPostByCategory(String category) {
        return postRepo.findFirstByCategoryOrderByCreationTimeDesc(category);
    }

    @Override
    public List<Post> getPostsByCategoryDescending(String category) {
        return postRepo.findAllByOrderByCreationTimeDesc();
    }
}
