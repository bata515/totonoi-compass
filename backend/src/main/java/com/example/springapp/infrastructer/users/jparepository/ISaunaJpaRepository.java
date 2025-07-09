package com.example.springapp.infrastructer.users.jparepository;

import com.example.springapp.infrastructer.users.dbmodel.SaunaDbModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ISaunaJpaRepository extends JpaRepository<SaunaDbModel, UUID> {
    List<SaunaDbModel> findAllByUserMail(String userMail);
}
