package com.example.springapp.infrastructer.users.repository;

import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import com.example.springapp.infrastructer.users.dbmodel.SaunaDbModel;
import com.example.springapp.infrastructer.users.jparepository.ISaunaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SaunaRepository implements ISaunaRepositoryInterface {
    @Autowired
    ISaunaJpaRepository saunaJpaRepository;

    @Override
    public List<Sauna> findAllByUserMail(String userMail) {
        return this.saunaJpaRepository.findAllByUserMail(userMail).stream().map(SaunaDbModel::adaptToSauna).collect(Collectors.toList());
    }
}
