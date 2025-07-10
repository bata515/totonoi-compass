package com.example.springapp.infrastructer.users.dbmodel;

import com.example.springapp.domain.domainobject.Sauna;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "saunas")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SaunaDbModel {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "visited")
    private boolean visited;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    public Sauna adaptToSauna() {
        return new Sauna(
                this.id,
                this.userMail,
                this.name,
                this.url,
                this.visited,
                this.created,
                this.modified
        );
    }

    public static SaunaDbModel adaptToSaunaDbModel(Sauna sauna) {
        return new SaunaDbModel(
                sauna.getId(),
                sauna.getUserMail(),
                sauna.getName(),
                sauna.getUrl(),
                sauna.isVisited(),
                sauna.getCreated(),
                sauna.getModified()
        );
    }

}
