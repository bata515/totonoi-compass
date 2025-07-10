package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.SaunaViewModel;
import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateSaunaService {
    @Autowired
    ISaunaRepositoryInterface saunaRepository;

    @Transactional
    public void updateSauna(SaunaViewModel saunaViewModel) {
        Sauna sauna =
                this.saunaRepository.findAllById(saunaViewModel.getId()).orElseThrow(
                        () -> new IllegalArgumentException("this sauna is not exist. id is: "+ saunaViewModel.getId())
                );

        Sauna updateSauna =
                sauna.updateSauna(saunaViewModel.getName(), saunaViewModel.getUrl(),
                        saunaViewModel.getVisited(), sauna.getCreated());

        this.saunaRepository.saveSauna(updateSauna);
    }
}
