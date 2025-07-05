package com.example.springapp.infrastructer.users.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springapp.infrastructer.users.dbmodel.UserDbModel;

import java.util.Optional;
import java.util.UUID;

public interface IUserJpaRepository extends JpaRepository<UserDbModel, UUID> {
    UserDbModel findAllById(UUID id);

    Optional<UserDbModel> findByMail(String mail);
}
