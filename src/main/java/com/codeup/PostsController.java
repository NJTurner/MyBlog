package com.codeup;

import com.codeup.auth.BaseController;
import com.codeup.model.Post;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostsController extends BaseController {

    @Autowired
    Posts postsDao;

    @GetMapping
    public String index(Model m){
        List<Post> posts = new ArrayList<>();
        postsDao.findAll().forEach(post -> posts.add(post));
        Collections.reverse(posts);
        m.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model m){
        m.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createNewPost(@Valid Post post, Errors validation, Model m){
        if(validation.hasErrors()){
            m.addAttribute("errors", validation);
            m.addAttribute("post", post);
            return "posts/create";
        }
        post.setOwner(loggedInUser());
        postsDao.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model m){
        Post post = postsDao.findOne(id);
        m.addAttribute("post", post);
        return"posts/showpage";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(Model m, @PathVariable long id){
        //TODO: use the passed id to find the record in the database
        Post post = postsDao.findOne(id);
        //todo: add to the model
        m.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@Valid Post postEdited, Errors val, Model m){
        if(val.hasErrors()){
            m.addAttribute("errors", val);
            m.addAttribute("post", postEdited);
            return "posts/edit";
        }
        Post newPost = postsDao.findOne(postEdited.getId());
        newPost.setTitle((postEdited.getTitle()));
        newPost.setBody(postEdited.getBody());
        postsDao.save(newPost);
        return "redirect:/posts/"+ postEdited.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable long id){
        postsDao.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/ads")
    public String viewAllPosts(Model model, @PageableDefault(value=10) Pageable pageable) {
        model.addAttribute("page", postsDao.findAll(pageable));
        return "posts/index";
    }

}