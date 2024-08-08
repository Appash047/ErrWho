package com.ErrWho.controller;

import com.ErrWho.entity.Post;
import com.ErrWho.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("ADMIN")
public class AdminController {

    @Autowired
    PostService postService;

    @GetMapping("/home")
    public String adminHomePage(Model model) {
        List<Post> posts = postService.findAllByOrderByCreationTimeDesc();
        model.addAttribute("posts", posts);
        return "admin-home";
    }


    @GetMapping("add-post")
    public String addPostPage() {
        return "add-post";
    }

    @PostMapping("/add-post")
    public String addPost(@RequestParam("category") String category, @RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("image") MultipartFile image, RedirectAttributes attributes) {
        try {
            Post post = new Post();
            post.setCategory(category);
            post.setTitle(title);
            post.setContent(content);
            post.setImage(image.getBytes());
            Post savedPost = postService.save(post);
            if (savedPost != null) {
                attributes.addFlashAttribute("message", "Post added successfully!");
                return "redirect:/ADMIN/home";
            } else {
                attributes.addFlashAttribute("message", "Failed to add post. Please try again.");
                return "add-post";
            }
        } catch (IOException e) {
            attributes.addFlashAttribute("message", "Failed to upload image. Please try again.");
            return "add-post";
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            postService.deleteById(id);
            attributes.addFlashAttribute("message", "Post deleted successfully.");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Error deleting post: " + e.getMessage());
        }
        return "redirect:/ADMIN/home";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Post> post = postService.findById(id);
        model.addAttribute("post", post.get());
        return "edit-post";
    }

    @PostMapping("/edit/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute("post") Post post, @RequestParam(value = "pic"
            , required = false) MultipartFile pic, RedirectAttributes attributes) {
        try {
            if (pic != null && !pic.isEmpty()) {
                byte[] imageBytes = pic.getBytes();
                post.setImage(imageBytes);
            }
            postService.updatePost(id, post);
            attributes.addFlashAttribute("message", "Post updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("message", "Error updating post: " + e.getMessage());
        }
        return "redirect:/ADMIN/home";
    }
}
