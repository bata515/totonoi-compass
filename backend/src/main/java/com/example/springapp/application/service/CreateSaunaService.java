package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.SaunaViewModel;
import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CreateSaunaService {
    @Autowired
    ISaunaRepositoryInterface saunaRepository;

    @Transactional
    public void createSauna(SaunaViewModel saunaViewModel) {
        Sauna sauna =
                Sauna.createSauna(UUID.randomUUID(),saunaViewModel.getUserMail(),
                        saunaViewModel.getName(),saunaViewModel.getUrl(),
                        saunaViewModel.getVisited()
                        );

        this.saunaRepository.saveSauna(sauna);
    }
}
