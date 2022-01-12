package com.tmaexample.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogonController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

}
