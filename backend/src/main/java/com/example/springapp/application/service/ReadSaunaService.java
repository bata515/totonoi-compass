package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.SaunaViewModel;
import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ReadSaunaService {
    @Autowired
    ISaunaRepositoryInterface saunaRepository;

    @Transactional
    public Optional<SaunaViewModel> readSaunaListByUserMail(String userMail) {
        List<Sauna> saunaList =
                this.saunaRepository.findAllByUserMail(userMail);

        if (!saunaList.isEmpty()) {
            int randomIndex = ThreadLocalRandom.current().nextInt(saunaList.size());
            Sauna resultSauna = saunaList.get(randomIndex);
            return Optional.of(SaunaViewModel.adaptToSaunaViewModel(resultSauna.getId(), resultSauna.getUserMail(), resultSauna.getName(), resultSauna.getUrl(), resultSauna.isVisited()));
        }
        return Optional.empty();
    }
}
