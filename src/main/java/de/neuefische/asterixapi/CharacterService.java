package de.neuefische.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Character saveCharacter(Character character){
        return repo.save(character);
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
}
