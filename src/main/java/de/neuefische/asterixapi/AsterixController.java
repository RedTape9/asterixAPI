package de.neuefische.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix")
@RequiredArgsConstructor

public class AsterixController {

    private final CharacterService service;


    @GetMapping
    public List<Character> getAllCharacters() {
        return service.getAllCharacters();
    }

    @GetMapping("/find/{name}")
    public Character findByName(@PathVariable String name){
        return service.findByName(name);
    }

    @PostMapping("/add")
    public Character saveCharacter(@RequestBody Character character){
        return service.saveCharacter(character);
    }

    @GetMapping("/{id}")
    public Character getById(@PathVariable String id){
        return service.getCharacterById(id);
    }

    @PutMapping("/{id}")
    public Character putCharacter(@PathVariable String id, @RequestBody Character character){
        return service.putCharacter(id, character);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCharacter(@PathVariable String id){
        service.deleteCharacter(id);
    }



}
