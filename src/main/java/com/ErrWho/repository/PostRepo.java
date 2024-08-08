package com.ErrWho.repository;

import com.ErrWho.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreationTimeDesc();

    List<Post> findByCategoryOrderByCreationTimeDesc(String category);

    Post findFirstByCategoryOrderByCreationTimeDesc(String category);



}
