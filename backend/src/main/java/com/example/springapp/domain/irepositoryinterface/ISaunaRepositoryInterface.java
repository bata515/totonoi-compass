package com.example.springapp.domain.irepositoryinterface;

import com.example.springapp.domain.domainobject.Saunas;

import java.util.List;

public interface ISaunaRepositoryInterface {

    public List<Saunas> findAllByUserMail(String userMail);
}
