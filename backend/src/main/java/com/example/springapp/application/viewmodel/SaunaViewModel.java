package com.example.springapp.application.viewmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
@Getter
public class SaunaViewModel {
    private final UUID id;
    private final String userMail;
    private final String name;
    private final String url;
    private final Boolean visited;


    public SaunaViewModel(UUID id, String userMail, String name, String url, boolean visited) {
        this.id = id;
        this.userMail = userMail;
        this.name = name;
        this.url = url;
        this.visited = visited;
    }

    public static SaunaViewModel adaptToSaunaViewModel(UUID id, String userMail, String name, String url, boolean visited) {
        return new SaunaViewModel(
                id, userMail,name,url,visited
        );
    }
}
