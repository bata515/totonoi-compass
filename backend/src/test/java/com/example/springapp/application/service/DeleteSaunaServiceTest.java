package com.example.springapp.application.service;

import com.example.springapp.domain.domainobject.Sauna;
import com.example.springapp.domain.irepositoryinterface.ISaunaRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteSaunaServiceTest {

    @Mock
    private ISaunaRepositoryInterface saunaRepository;

    @InjectMocks
    private DeleteSaunaService deleteSaunaService;

    private UUID testSaunaId;
    private Sauna testSauna;

    @BeforeEach
    void setUp() {
        testSaunaId = UUID.randomUUID();
        testSauna = Sauna.createSauna(
            testSaunaId,
            "test@example.com",
            "テストサウナ",
            "https://test-sauna.com",
            true
        );
    }

    @Test
    void deleteSaunaById_正常ケース() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(testSauna));
        doNothing().when(saunaRepository).deleteById(testSaunaId);

        // When
        deleteSaunaService.deleteSaunaById(testSaunaId);

        // Then
        verify(saunaRepository).findAllById(testSaunaId);
        verify(saunaRepository).deleteById(testSaunaId);
    }

    @Test
    void deleteSaunaById_サウナが存在しない場合_例外が発生() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> deleteSaunaService.deleteSaunaById(testSaunaId)
        );

        assertEquals("this sauna is not exist. id is: " + testSaunaId, exception.getMessage());
        verify(saunaRepository).findAllById(testSaunaId);
        verify(saunaRepository, never()).deleteById(any(UUID.class));
    }

    @Test
    void deleteSaunaById_リポジトリメソッドが正しい順序で呼び出される() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(testSauna));
        doNothing().when(saunaRepository).deleteById(testSaunaId);

        // When
        deleteSaunaService.deleteSaunaById(testSaunaId);

        // Then
        var inOrder = inOrder(saunaRepository);
        inOrder.verify(saunaRepository).findAllById(testSaunaId);
        inOrder.verify(saunaRepository).deleteById(testSaunaId);
    }

    @Test
    void deleteSaunaById_正しいIDで削除される() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(testSauna));
        doNothing().when(saunaRepository).deleteById(testSaunaId);

        // When
        deleteSaunaService.deleteSaunaById(testSaunaId);

        // Then
        verify(saunaRepository).deleteById(testSaunaId);
    }
}
