package org.pokemon_collection.pokemon_collection.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class myUser {
    @Id
    private String username;
    
}
