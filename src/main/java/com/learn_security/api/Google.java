package com.learn_security.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login/oauth2/code/google")
public class Google {
    @GetMapping
    public String helloworld(){
        return "hello";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
