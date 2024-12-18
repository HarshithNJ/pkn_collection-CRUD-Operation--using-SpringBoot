package org.pokemon_collection.pokemon_collection.service;

import java.util.Optional;

import org.pokemon_collection.pokemon_collection.dto.myUser;
import org.pokemon_collection.pokemon_collection.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class userService {

    @Autowired
    userRepository repository;

    public void removeMessage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().removeAttribute("message");
    }

    public String register(myUser user, HttpSession session) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            if (repository.existsById(user.getUsername())) {
                session.setAttribute("message", "Username Already Exists");
                return "redirect:/register";
            } else {
                repository.save(user);
                session.setAttribute("message", "Account Created Success");
                return "redirect:/";
            }
        }
         else {
            session.setAttribute("message", "Password and Confirm Password Missmatch");
            return "redirect:/register";
        }
    }

    public String login(String username, String password, HttpSession session) {
        Optional<myUser> user = repository.findById(username);
        if (user.isEmpty()) {
            session.setAttribute("message", "Invalid Username");
            return "redirect:/";
        } else {
            if (password.equals(user.get().getPassword())) {
                session.setAttribute("message", "Login Success");
                session.setAttribute("user", user.get());
                return "redirect:/home";
            } else {
                session.setAttribute("message", "Invalid Password");
                return "redirect:/";
            }
        }
    }
    
}
