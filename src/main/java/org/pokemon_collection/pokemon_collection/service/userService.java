package org.pokemon_collection.pokemon_collection.service;

import org.pokemon_collection.pokemon_collection.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    userRepository repository;
    
}
