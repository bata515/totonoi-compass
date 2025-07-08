package com.example.springapp.application.users.bodymodel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserBodyModel {
    private String familyName;
    private String familyNameRuby;
    private String firstName;
    private String firstNameRuby;
    private String mail;
    private String password;
}
