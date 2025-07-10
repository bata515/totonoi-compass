package com.example.springapp.infrastructer.users.repository;

import com.example.springapp.domain.domainobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;
import com.example.springapp.infrastructer.users.dbmodel.UserDbModel;
import com.example.springapp.infrastructer.users.jparepository.IUserJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepositoryInterface {
    @Autowired
    IUserJpaRepository userJpaRepository;

    @Override
    public User findById(UUID id) {
        return this.userJpaRepository.findAllById(id).adaptToUser();
    }

    @Override
    public User createUser(User user) {
        this.userJpaRepository.save(UserDbModel.adaptToUserDbModel(user));
        return this.userJpaRepository.findAllById(user.getId()).adaptToUser();
    }

    @Override
    public Optional<User> findByMail(String mail) {
        return this.userJpaRepository.findByMail(mail).map(UserDbModel::adaptToUser);
    }
}
