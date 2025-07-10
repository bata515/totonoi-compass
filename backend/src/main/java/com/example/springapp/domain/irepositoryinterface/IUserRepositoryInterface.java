package com.example.springapp.domain.irepositoryinterface;

import java.util.Optional;
import java.util.UUID;

import com.example.springapp.domain.domainobject.User;

public interface IUserRepositoryInterface {

    public User findById(UUID id);

    public User createUser(User user);

    public Optional<User> findByMail(String mail);
}
