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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateSaunaServiceTest {

    @Mock
    private ISaunaRepositoryInterface saunaRepository;

    @InjectMocks
    private UpdateSaunaService updateSaunaService;

    private UUID testSaunaId;
    private SaunaViewModel saunaViewModel;
    private Sauna existingSauna;

    @BeforeEach
    void setUp() {
        testSaunaId = UUID.randomUUID();
        
        saunaViewModel = new SaunaViewModel(
            testSaunaId,
            "test@example.com",
            "更新されたサウナ名",
            "https://updated-sauna.com",
            false
        );
        
        existingSauna = Sauna.createSauna(
            testSaunaId,
            "test@example.com",
            "元のサウナ名",
            "https://original-sauna.com",
            true
        );
    }

    @Test
    void updateSauna_正常ケース() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(existingSauna));
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        updateSaunaService.updateSauna(saunaViewModel);

        // Then
        verify(saunaRepository).findAllById(testSaunaId);
        verify(saunaRepository).saveSauna(any(Sauna.class));
    }

    @Test
    void updateSauna_サウナが存在しない場合_例外が発生() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> updateSaunaService.updateSauna(saunaViewModel)
        );

        assertEquals("this sauna is not exist. id is: " + testSaunaId, exception.getMessage());
        verify(saunaRepository).findAllById(testSaunaId);
        verify(saunaRepository, never()).saveSauna(any(Sauna.class));
    }

    @Test
    void updateSauna_更新されたサウナが保存される() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(existingSauna));
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        updateSaunaService.updateSauna(saunaViewModel);

        // Then
        verify(saunaRepository).saveSauna(argThat(sauna -> 
            sauna.getId().equals(testSaunaId) &&
            sauna.getName().equals("更新されたサウナ名") &&
            sauna.getUrl().equals("https://updated-sauna.com") &&
            sauna.isVisited() == false &&
            sauna.getUserMail().equals("test@example.com")
        ));
    }

    @Test
    void updateSauna_リポジトリメソッドが正しい順序で呼び出される() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(existingSauna));
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        updateSaunaService.updateSauna(saunaViewModel);

        // Then
        var inOrder = inOrder(saunaRepository);
        inOrder.verify(saunaRepository).findAllById(testSaunaId);
        inOrder.verify(saunaRepository).saveSauna(any(Sauna.class));
    }

    @Test
    void updateSauna_元のサウナの作成日時が保持される() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(existingSauna));
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        updateSaunaService.updateSauna(saunaViewModel);

        // Then
        verify(saunaRepository).saveSauna(argThat(sauna -> 
            sauna.getCreated().equals(existingSauna.getCreated())
        ));
    }

    @Test
    void updateSauna_部分的な更新() {
        // Given
        SaunaViewModel partialUpdateViewModel = new SaunaViewModel(
            testSaunaId,
            "test@example.com",
            "部分更新サウナ",
            null, // URLはnull
            true
        );
        
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(existingSauna));
        doNothing().when(saunaRepository).saveSauna(any(Sauna.class));

        // When
        updateSaunaService.updateSauna(partialUpdateViewModel);

        // Then
        verify(saunaRepository).saveSauna(argThat(sauna -> 
            sauna.getName().equals("部分更新サウナ") &&
            sauna.getUrl() == null &&
            sauna.isVisited() == true
        ));
    }
}
