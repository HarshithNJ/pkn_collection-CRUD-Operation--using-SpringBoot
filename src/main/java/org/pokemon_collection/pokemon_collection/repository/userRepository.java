package org.pokemon_collection.pokemon_collection.repository;

import org.pokemon_collection.pokemon_collection.dto.myUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<myUser, String>{

}
