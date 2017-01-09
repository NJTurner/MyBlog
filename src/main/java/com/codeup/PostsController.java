package com.codeup;

import com.codeup.model.Post;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    public String createNewPost(@Valid Post post, Errors validation, Model m){
        if(validation.hasErrors()){
            m.addAttribute("errors", validation);
            m.addAttribute("post", post);
            return "posts/create";

        }
        DaoFactory.getPostsDao().save(post);
       //DaoFactory.getPostsDao().insert(post);
        return "redirect:/posts";
    }

    @GetMapping("posts/{id}")
    public String show(@PathVariable int id, Model m){
        Post post = DaoFactory.getPostsDao().findById(id);
        m.addAttribute("post", post);
        return"posts/showpage";
    }

    @GetMapping("posts/{id}/edit")
    public String showEditForm(Model m, @PathVariable int id){
        //TODO: use the passed id to find the record in the database
        Post post = DaoFactory.getPostsDao().findById(id);
        //todo: add to the model
        m.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("posts/{id}/edit")
    public String edit(@Valid Post postEdited, Errors val, Model m){
        if(val.hasErrors()){
            m.addAttribute("errors", val);
            m.addAttribute("post", postEdited);
            return "posts/edit";
        }
        Post newPost = DaoFactory.getPostsDao().findById(postEdited.getId());
        newPost.setTitle((postEdited.getTitle()));
        newPost.setBody(postEdited.getBody());
        DaoFactory.getPostsDao().update(newPost);
        return "redirect:/posts/"+ postEdited.getId();
    }

    @PostMapping("posts/{id}/delete")
    public String delete(@PathVariable int id){
        DaoFactory.getPostsDao().delete(id);
        return "redirect:/posts";
    }
}