package com.example.springapp.application.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserViewModel {
    private String familyName;
    private String familyNameRuby;
    private String firstName;
    private String firstNameRuby;
    private String mail;
    private String password;
}
