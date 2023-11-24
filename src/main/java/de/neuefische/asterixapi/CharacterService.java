package de.neuefische.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepo repo;

    public List<Character> getAllCharacters() {
        return repo.findAll();
    }

    public Character getCharacterById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public Character saveCharacter(NewCharacterDTO character){
        Character newCharacter = new Character(UUID.randomUUID().toString(), character.name(), character.age(), character.occupation());

        return repo.save(newCharacter);
    }

    public Character findByName(String name) {
        return repo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }


    public Character putCharacter(String id, Character character) {
        Character existingCharacter = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        Character updatedCharacter = existingCharacter
                .withName(character.name())
                .withAge(character.age())
                .withOccupation(character.occupation());
        return repo.save(updatedCharacter);
    }

    public void deleteCharacter(String id) {
        repo.deleteById(id);
    }


    public List<Character> filterByAge(int age) {
        return repo.findByAge(age);
    }

    public List<Character> filterByOccupation(String occupation) {
        return repo.findByOccupation(occupation);
    }

    public List<Character> getCharactersByAgeAndOccupation(Integer age, String occupation) {
        if (age != null && occupation != null) {
            return repo.findByAgeAndOccupation(age, occupation);
        } else if (age != null) {
            return repo.findByAge(age);
        } else if (occupation != null) {
            return repo.findByOccupation(occupation);
        } else {
            return repo.findAll();
        }
    }

    public Double getAverageAgeByOccupation(String occupation) {
        return repo.findAverageAgeByOccupation(occupation);
    }




}
