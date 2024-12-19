package org.pokemon_collection.pokemon_collection.repository;

import java.util.List;

import org.pokemon_collection.pokemon_collection.dto.myUser;
import org.pokemon_collection.pokemon_collection.dto.pokemonData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface pokemonRepository extends JpaRepository<pokemonData, Integer> {

    List<pokemonData> findByUser(myUser user);

}
