package com.example.springapp.application.viewmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
@Getter
public class UserViewModel {
    private final UUID id;
    private final String familyName;
    private final String familyNameRuby;
    private final String firstName;
    private final String firstNameRuby;
    private final String mail;
    private final String password;

    public UserViewModel(UUID id, String familyName,String familyNameRuby, String firstName, String firstNameRuby,String mail, String password) {
        this.id = id;
        this.familyName = familyName;
        this.familyNameRuby = familyNameRuby;
        this.firstName = firstName;
        this.firstNameRuby = firstNameRuby;
        this.mail = mail;
        this.password = password;
    }

    public static UserViewModel adaptToUserViewModel(UUID id, String familyName,String familyNameRuby, String firstName, String firstNameRuby,String mail, String password) {
        return new UserViewModel(
                id, familyName,familyNameRuby,firstName, firstNameRuby, mail, password
        );
    }
}
