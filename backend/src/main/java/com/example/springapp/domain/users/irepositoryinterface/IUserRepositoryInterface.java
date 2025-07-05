package com.example.springapp.domain.users.irepositoryinterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springapp.domain.users.domainobject.User;

public interface IUserRepositoryInterface {

    public List<User> findAll();

    public User findById(UUID id);

    public User createUser(User user);

    public Optional<User> findByMail(String mail);
}
