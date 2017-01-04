package com.codeup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class HelloController {

   //@RequestMapping(path = "/hello", method = RequestMethod.GET) //same as @GetMapping
    @GetMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable String name) {
        return "<h1>Hello, " + name + "!</h1>";
    }

    @GetMapping("/increment/{number}")
    @ResponseBody
    public String increment(@PathVariable int number){
        return number + "plus one is " + (number+1)+ "!";
    }
}