package de.neuefische.asterixapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public Character saveCharacter(@RequestBody NewCharacterDTO character){
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

   @GetMapping("/filter/age/{age}")
    public List<Character> filterByAge(@PathVariable int age){
        return service.filterByAge(age);
    }

    @GetMapping("/filter/occupation/{occupation}")
    public List<Character> filterByOccupation(@PathVariable String occupation){
        return service.filterByOccupation(occupation);
    }

    @GetMapping("/filter")
    // Example: http://localhost:8080/api/asterix/filter?age=5&occupation=Hund
    public List<Character> getCharactersByAgeAndOccupation(
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String occupation) {

        return service.getCharactersByAgeAndOccupation(age, occupation);
    }

    @GetMapping("/averageage")
    // Example: http://localhost:8080/api/asterix/averageage?occupation=Hund
    public Double getAverageAgeByOccupation(@RequestParam String occupation) {
        return service.getAverageAgeByOccupation(occupation);
    }



}
