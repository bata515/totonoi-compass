package com.example.springapp.domain.irepositoryinterface;

import com.example.springapp.domain.domainobject.Sauna;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ISaunaRepositoryInterface {

    public List<Sauna> findAllByUserMail(String userMail);

    public Optional<Sauna> findAllById(UUID id);

    void saveSauna(Sauna sauna);
}
