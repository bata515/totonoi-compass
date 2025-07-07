package com.example.springapp.domain.domainobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Sauna {
    private final UUID id;
    private final UUID userId;
    private final String name;
    private final String url;
    private final boolean visited;
    private final LocalDateTime created;
    private final LocalDateTime modified;

    public Sauna(UUID id, UUID userId, String name, String url, boolean visited, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.url = url;
        this.visited = visited;
        this.created = created;
        this.modified = modified;
    }

    public static Sauna createSauna(UUID id, UUID userId, String name, String url, boolean visited, LocalDateTime created, LocalDateTime modified) {
        return new Sauna(
                id, userId, name, url, visited, created, modified
        );
    }
}
  
