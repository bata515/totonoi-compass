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
    private final String userMail;
    private final String name;
    private final String url;
    private final boolean visited;
    private final LocalDateTime created;
    private final LocalDateTime modified;

    public Sauna(UUID id, String userMail, String name, String url, boolean visited, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.userMail = userMail;
        this.name = name;
        this.url = url;
        this.visited = visited;
        this.created = created;
        this.modified = modified;
    }

    public static Sauna createSauna(UUID id, String userMail, String name, String url, boolean visited) {
        return new Sauna(
                id, userMail, name, url, visited, LocalDateTime.now(), LocalDateTime.now()
        );
    }

    public Sauna updateSauna(String name, String url, boolean visited, LocalDateTime created) {
        return new Sauna(
                this.id, this.userMail, name, url, visited, created,LocalDateTime.now()
        );
    }
}
  
