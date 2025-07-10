package com.example.springapp.domain.irepositoryinterface;

import com.example.springapp.domain.domainobject.Sauna;

import java.util.List;

public interface ISaunaRepositoryInterface {

    public List<Sauna> findAllByUserMail(String userMail);
}
