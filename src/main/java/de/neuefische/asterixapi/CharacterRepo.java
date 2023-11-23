package de.neuefische.asterixapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepo extends MongoRepository<Character, String>{
    Optional<Character> findByName(String name);
}