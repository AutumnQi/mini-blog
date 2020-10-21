package com.autumn.blog.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.autumn.blog.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

}
