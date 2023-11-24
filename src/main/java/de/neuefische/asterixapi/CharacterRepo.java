package de.neuefische.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface CharacterRepo extends MongoRepository<Character, String>{
    Optional<Character> findByName(String name);

    List<Character> findByAge(int age);

    List<Character> findByOccupation(String occupation);

    List<Character> findByAgeAndOccupation(Integer age, String occupation);

    // Eine benutzerdefinierte Methode, um den Durchschnitt des Alters zu berechnen
    @Aggregation(pipeline = {
            "{ $match: { 'occupation': ?0 } }",
            "{ $group: { _id: null, averageAge: { $avg: '$age' } } }"
    })
    Double findAverageAgeByOccupation(String occupation);

}