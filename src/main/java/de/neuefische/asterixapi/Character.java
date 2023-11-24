package de.neuefische.asterixapi;

import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("character")
@With
public record Character(String id, String name, int age, String occupation) {
    public Character(String name, int age, String occupation) {
        this(UUID.randomUUID().toString(), name, age, occupation);
    }


}
