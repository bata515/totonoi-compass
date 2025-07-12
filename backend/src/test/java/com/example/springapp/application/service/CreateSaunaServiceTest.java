package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.SaunaViewModel;
import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSaunaServiceTest {

    @Mock
    private ISaunaRepositoryInterface saunaRepository;

    @InjectMocks
    private CreateSaunaService createSaunaService;

    private SaunaViewModel saunaViewModel;

    @BeforeEach
    void setUp() {
        saunaViewModel = new SaunaViewModel(
            UUID.randomUUID(),
            "test@example.com",
            "テストサウナ",
            "https://test-sauna.com",
            true
        );
    }

    @Test
    void createSauna_正常ケース() {
        // Given
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        createSaunaService.createSauna(saunaViewModel);

        // Then
        verify(saunaRepository, times(1)).saveSauna(any(Sauna.class));
    }

    @Test
    void createSauna_リポジトリが呼び出される() {
        // Given
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        createSaunaService.createSauna(saunaViewModel);

        // Then
        verify(saunaRepository).saveSauna(argThat(sauna -> 
            sauna.getUserMail().equals("test@example.com") &&
            sauna.getName().equals("テストサウナ") &&
            sauna.getUrl().equals("https://test-sauna.com") &&
            sauna.isVisited() == true
        ));
    }
}
