package com.ErrWho.service;

import com.ErrWho.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(Post post);

    List<Post> findAllByOrderByCreationTimeDesc();

    Optional<Post> findById(Long id);

    void updatePost(Long id, Post updatedPost);

    void deleteById(Long id);

    Post getLatestPostByCategory(String category);

    List<Post> getPostsByCategoryDescending(String category);

}
