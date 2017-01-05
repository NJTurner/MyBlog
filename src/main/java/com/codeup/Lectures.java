package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Lectures{
    @GetMapping("/formatting")
    public String formatting(Model m){
        m.addAttribute("productName","500GB PS4");
        m.addAttribute("productPrice", 1234.3436);
        m.addAttribute("productDate", "01/04/2017");
        return "formatting";
    }

    @GetMapping("/conditions/{num}")
    public String conditions(@PathVariable String num, Model m){
        m.addAttribute("status", num);
        m.addAttribute("number", 1);
        return "conditions";
    }
}