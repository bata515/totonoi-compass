package com.example.springapp.domain.irepositoryinterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springapp.domain.domainobject.Users;

public interface IUserRepositoryInterface {

    public Users findById(UUID id);

    public Users createUser(Users users);

    public Optional<Users> findByMail(String mail);
}
