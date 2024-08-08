package com.ErrWho.controller;

import com.ErrWho.entity.Post;
import com.ErrWho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/category/{category}")
    public String categoryPostPage(@PathVariable String category, Model model) {
        List<Post> posts = postService.getPostsByCategoryDescending(category);
        model.addAttribute("posts","posts");
        model.addAttribute("postsCategory",category);
        return "category-post";
    }

    @GetMapping("post")
    public String postPage() {
        return "post";
    }

    @GetMapping("session")
    public String sessionPage() {
        return "session";
    }

    @GetMapping("about-us")
    public String aboutUsPage() {
        return "about-us";
    }

    @GetMapping("contact-us")
    public String contactUsPage() {
        return "contact-us";
    }


//    @GetMapping("/category/{category}")
//    @ResponseBody
//    public ResponseEntity<Post> getLatestPostByCategory(@PathVariable String category) {
//        Post latestPost = postService.getLatestPostByCategory(category);
//        return ResponseEntity.ok(latestPost);
//    }
//
//    @GetMapping("/category/{category}")
//    @ResponseBody
//    public ResponseEntity<List<Post>> getPostsByCategoryDescending(@PathVariable String category) {
//        List<Post> posts = postService.getPostsByCategoryDescending(category);
//        return ResponseEntity.ok(posts);
//    }

    @GetMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getUserPhoto(@PathVariable Long id) {
        Optional<Post> post = postService.findById(id);
        Post post1 = post.get();
        if (post1 != null && post1.getImage() != null) {
            ByteArrayResource resource = new ByteArrayResource(post1.getImage());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).contentLength(post1.getImage().length).body(resource);
        }
        return ResponseEntity.notFound().build();
    }

}
