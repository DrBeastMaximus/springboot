package com.tmaexample.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class LogonController {
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin.html";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403.html";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login.html";
    }

}
