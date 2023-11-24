package de.neuefische.asterixapi;


import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CharacterServiceTest {


    CharacterRepo characterRepo = mock(CharacterRepo.class);
    IdService idService = mock(IdService.class);
    CharacterService characterService = new CharacterService(characterRepo, idService);
    @Test
    void testGetAllCharacters_whenCalled_thenReturnsAllPersons() {
        //GIVEN
        when(characterRepo.findAll()).thenReturn(List.of(
                new Character("1", "Asterix", 30, "Gaul"),
                new Character("2", "Obelix", 32, "Gaul")
        ));
        //WHEN
        List<Character> actual = characterService.getAllCharacters();
        //THEN
        List<Character> expected = List.of(
                new Character("1", "Asterix", 30, "Gaul"),
                new Character("2", "Obelix", 32, "Gaul")
        );
        verify(characterRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void testSaveCharacter_whenCalled_thenReturnsSavedCharacter() {
        //GIVEN
        NewCharacterDTO newCharacterDTO = new NewCharacterDTO("Asterix", 30, "Gaul");
        Character expected = new Character("testId", "Asterix", 30, "Gaul");
        when(characterRepo.save(new Character("testId","Asterix", 30, "Gaul"))).thenReturn(expected);
        when(idService.generateId()).thenReturn("testId");
        //WHEN
        Character actual = characterService.saveCharacter(newCharacterDTO);
        //THEN
        verify(characterRepo).save(new Character("testId","Asterix", 30, "Gaul"));
        assertEquals(expected, actual);
    }


    @Test
    void testGetCharacterById_whenCalled_thenReturnsCharacterWithId() {
        //GIVEN
        when(characterRepo.findById("1")).thenReturn(java.util.Optional.of(new Character("1", "Asterix", 30, "Gaul")));
        //WHEN
        Character actual = characterService.getCharacterById("1");
        //THEN
        Character expected = new Character("1", "Asterix", 30, "Gaul");
        verify(characterRepo).findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void testUpdateCharacter_whenCalled_thenReturnsUpdatedCharacter() {
        //GIVEN
        Character character = new Character("1", "Asterix", 30, "Gaul");
        when(characterRepo.findById("1")).thenReturn(java.util.Optional.of(character));
        when(characterRepo.save(character)).thenReturn(character);
        //WHEN
        Character actual = characterService.putCharacter("1", character);
        //THEN
        Character expected = new Character("1", "Asterix", 30, "Gaul");
        verify(characterRepo).findById("1");
        verify(characterRepo).save(character);
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteCharacterById_whenCalled_thenReturnsNothing() {

        //GIVEN
        Character character = new Character("1", "Asterix", 30, "Gaul");
        when(characterRepo.findById("1")).thenReturn(Optional.of(character));
        //WHEN
        characterService.deleteCharacter("1");
        //THEN
        verify(characterRepo).deleteById("1");

    }
}
