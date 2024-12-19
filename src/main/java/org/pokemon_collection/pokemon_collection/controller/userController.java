package org.pokemon_collection.pokemon_collection.controller;

import org.pokemon_collection.pokemon_collection.dto.myUser;
import org.pokemon_collection.pokemon_collection.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;





@Controller
public class userController {

    @Autowired
    userService service;

    @GetMapping("/")
    public String loadLogin() {
        return "Login.html";
    }
    
    @GetMapping("/register")
    public String loadRegister() {
        return "Register.html";
    }

    @PostMapping("/register")
    public String register(myUser user, HttpSession session) {
        
        return service.register(user, session);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        return service.login(username, password, session);
    }
    
    @GetMapping("/home")
    public String loadHome() {
        return "home.html";
    }
    
    @GetMapping("/logout")
    public String LogOut(HttpSession session){
        return service.LogOut(session);
    }
    
}
