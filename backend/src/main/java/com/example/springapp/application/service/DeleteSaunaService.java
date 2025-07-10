package com.example.springapp.application.service;

import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteSaunaService {
    @Autowired
    ISaunaRepositoryInterface saunaRepository;

    public void deleteSaunaById(UUID id) {

        Sauna sauna =
                this.saunaRepository.findAllById(id).orElseThrow(
                        () -> new IllegalArgumentException("this sauna is not exist. id is: "+ id)
                );
        this.saunaRepository.deleteById(sauna.getId());
    }


}
