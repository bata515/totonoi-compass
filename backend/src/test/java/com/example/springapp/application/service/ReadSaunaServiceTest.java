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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReadSaunaServiceTest {

    @Mock
    private ISaunaRepositoryInterface saunaRepository;

    @InjectMocks
    private ReadSaunaService readSaunaService;

    private String testUserMail;
    private UUID testSaunaId;
    private Sauna testSauna1;
    private Sauna testSauna2;
    private List<Sauna> testSaunaList;

    @BeforeEach
    void setUp() {
        testUserMail = "test@example.com";
        testSaunaId = UUID.randomUUID();
        
        testSauna1 = Sauna.createSauna(
            UUID.randomUUID(),
            testUserMail,
            "テストサウナ1",
            "https://test-sauna1.com",
            true
        );
        
        testSauna2 = Sauna.createSauna(
            UUID.randomUUID(),
            testUserMail,
            "テストサウナ2",
            "https://test-sauna2.com",
            false
        );
        
        testSaunaList = Arrays.asList(testSauna1, testSauna2);
    }

    @Test
    void readRandomSaunaByUserMail_正常ケース_サウナが存在する() {
        // Given
        when(saunaRepository.findAllByUserMail(testUserMail)).thenReturn(testSaunaList);

        // When
        Optional<SaunaViewModel> result = readSaunaService.readRandomSaunaByUserMail(testUserMail);

        // Then
        assertTrue(result.isPresent());
        SaunaViewModel saunaViewModel = result.get();
        assertEquals(testUserMail, saunaViewModel.getUserMail());
        assertTrue(saunaViewModel.getName().equals("テストサウナ1") || 
                  saunaViewModel.getName().equals("テストサウナ2"));
        verify(saunaRepository).findAllByUserMail(testUserMail);
    }

    @Test
    void readRandomSaunaByUserMail_サウナが存在しない場合() {
        // Given
        when(saunaRepository.findAllByUserMail(testUserMail)).thenReturn(Collections.emptyList());

        // When
        Optional<SaunaViewModel> result = readSaunaService.readRandomSaunaByUserMail(testUserMail);

        // Then
        assertFalse(result.isPresent());
        verify(saunaRepository).findAllByUserMail(testUserMail);
    }

    @Test
    void readSaunaListsByUserMail_正常ケース() {
        // Given
        when(saunaRepository.findAllByUserMail(testUserMail)).thenReturn(testSaunaList);

        // When
        List<SaunaViewModel> result = readSaunaService.readSaunaListsByUserMail(testUserMail);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        
        SaunaViewModel sauna1 = result.get(0);
        assertEquals(testSauna1.getName(), sauna1.getName());
        assertEquals(testSauna1.getUserMail(), sauna1.getUserMail());
        assertEquals(testSauna1.getUrl(), sauna1.getUrl());
        assertEquals(testSauna1.isVisited(), sauna1.getVisited());
        
        SaunaViewModel sauna2 = result.get(1);
        assertEquals(testSauna2.getName(), sauna2.getName());
        assertEquals(testSauna2.getUserMail(), sauna2.getUserMail());
        assertEquals(testSauna2.getUrl(), sauna2.getUrl());
        assertEquals(testSauna2.isVisited(), sauna2.getVisited());
        
        verify(saunaRepository).findAllByUserMail(testUserMail);
    }

    @Test
    void readSaunaListsByUserMail_空のリストが返される() {
        // Given
        when(saunaRepository.findAllByUserMail(testUserMail)).thenReturn(Collections.emptyList());

        // When
        List<SaunaViewModel> result = readSaunaService.readSaunaListsByUserMail(testUserMail);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(saunaRepository).findAllByUserMail(testUserMail);
    }

    @Test
    void readSaunaById_正常ケース() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.of(testSauna1));

        // When
        SaunaViewModel result = readSaunaService.readSaunaById(testSaunaId);

        // Then
        assertNotNull(result);
        assertEquals(testSauna1.getId(), result.getId());
        assertEquals(testSauna1.getName(), result.getName());
        assertEquals(testSauna1.getUserMail(), result.getUserMail());
        assertEquals(testSauna1.getUrl(), result.getUrl());
        assertEquals(testSauna1.isVisited(), result.getVisited());
        verify(saunaRepository).findAllById(testSaunaId);
    }

    @Test
    void readSaunaById_サウナが存在しない場合_例外が発生() {
        // Given
        when(saunaRepository.findAllById(testSaunaId)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> readSaunaService.readSaunaById(testSaunaId)
        );

        assertEquals("this sauna is not exist. id is: " + testSaunaId, exception.getMessage());
        verify(saunaRepository).findAllById(testSaunaId);
    }
}
