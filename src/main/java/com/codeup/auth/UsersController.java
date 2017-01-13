package com.codeup.auth;


import com.codeup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UsersController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Users usersDao;

    @GetMapping("/register")
    public String showRegisterForm(Model m){
        m.addAttribute("owner",new User());
    return "users/register";
    }

    @PostMapping("/users/register")
    public String registerUser(@Valid User newUser, Errors val, Model m){
        if(val.hasErrors()){
            m.addAttribute("errors", val);
            m.addAttribute("user", newUser);
            return "users/register";
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usersDao.save(newUser);
        return "redirect:/posts/";
    }


        @GetMapping("/users/{id}")
    public String showProfile(@PathVariable long id, Model m){
    return "users/profile";
        }

        @GetMapping("/login")
        public String showLogin() {
        return "users/login";
        }

}
