package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResuméController {

    @GetMapping("/resume")
    public String resume() {
        return "resume";
    }
}