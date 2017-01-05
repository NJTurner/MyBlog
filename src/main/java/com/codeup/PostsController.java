package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostsController {

    @GetMapping("/posts")
    public String index(Model m){
        List<Post> posts = DaoFactory.getPostsDao().all();
        m.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(Model m){
        m.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String createNewPost(@ModelAttribute Post post){
        System.out.println(post);
        DaoFactory.getPostsDao().save(post);
       //DaoFactory.getPostsDao().insert(post);
        return "redirect:/posts";
    }
}