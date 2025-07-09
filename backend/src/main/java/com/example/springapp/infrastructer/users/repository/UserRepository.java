package com.example.springapp.infrastructer.users.repository;

import com.example.springapp.domain.domainobject.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;
import com.example.springapp.infrastructer.users.dbmodel.UserDbModel;
import com.example.springapp.infrastructer.users.jparepository.IUserJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepository implements IUserRepositoryInterface {
    @Autowired
    IUserJpaRepository userJpaRepository;

    @Override
    public Users findById(UUID id) {
        return this.userJpaRepository.findAllById(id).adaptToUser();
    }

    @Override
    public Users createUser(Users users) {
        this.userJpaRepository.save(UserDbModel.adaptToUserDbModel(users));
        return this.userJpaRepository.findAllById(users.getId()).adaptToUser();
    }

    @Override
    public Optional<Users> findByMail(String mail) {
        return this.userJpaRepository.findByMail(mail).map(UserDbModel::adaptToUser);
    }
}
