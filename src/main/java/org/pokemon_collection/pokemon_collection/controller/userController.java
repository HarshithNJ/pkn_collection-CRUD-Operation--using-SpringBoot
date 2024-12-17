package org.pokemon_collection.pokemon_collection.controller;

import org.pokemon_collection.pokemon_collection.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class userController {

    @Autowired
    userService service;
    
}
