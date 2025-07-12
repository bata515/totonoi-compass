package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.UserViewModel;
import com.example.springapp.domain.domainobject.User;
import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReadUserServiceTest {

    @Mock
    private IUserRepositoryInterface userRepository;

    @InjectMocks
    private ReadUserService readUserService;

    private UUID testUserId;
    private String testUserMail;
    private User testUser;

    @BeforeEach
    void setUp() {
        testUserId = UUID.randomUUID();
        testUserMail = "test@example.com";
        
        testUser = new User(
            testUserId,
            "田中",
            "タナカ",
            "太郎",
            "タロウ",
            testUserMail,
            "encodedPassword",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    @Test
    void readUserById_正常ケース() {
        // Given
        when(userRepository.findById(testUserId)).thenReturn(testUser);

        // When
        UserViewModel result = readUserService.readUserById(testUserId);

        // Then
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getFamilyName(), result.getFamilyName());
        assertEquals(testUser.getFamilyNameRuby(), result.getFamilyNameRuby());
        assertEquals(testUser.getFirstName(), result.getFirstName());
        assertEquals(testUser.getFirstNameRuby(), result.getFirstNameRuby());
        assertEquals(testUser.getMail(), result.getMail());
        assertEquals(testUser.getPassword(), result.getPassword());
        verify(userRepository).findById(testUserId);
    }

    @Test
    void readUserByMail_正常ケース() {
        // Given
        when(userRepository.findByMail(testUserMail)).thenReturn(Optional.of(testUser));

        // When
        UserViewModel result = readUserService.readUserByMail(testUserMail);

        // Then
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getFamilyName(), result.getFamilyName());
        assertEquals(testUser.getFamilyNameRuby(), result.getFamilyNameRuby());
        assertEquals(testUser.getFirstName(), result.getFirstName());
        assertEquals(testUser.getFirstNameRuby(), result.getFirstNameRuby());
        assertEquals(testUser.getMail(), result.getMail());
        assertEquals(testUser.getPassword(), result.getPassword());
        verify(userRepository).findByMail(testUserMail);
    }

    @Test
    void readUserByMail_ユーザーが存在しない場合_例外が発生() {
        // Given
        when(userRepository.findByMail(testUserMail)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> readUserService.readUserByMail(testUserMail)
        );

        assertEquals("User not found with email: " + testUserMail, exception.getMessage());
        verify(userRepository).findByMail(testUserMail);
    }

    @Test
    void readUserById_リポジトリが正しく呼び出される() {
        // Given
        when(userRepository.findById(testUserId)).thenReturn(testUser);

        // When
        readUserService.readUserById(testUserId);

        // Then
        verify(userRepository, times(1)).findById(testUserId);
    }

    @Test
    void readUserByMail_リポジトリが正しく呼び出される() {
        // Given
        when(userRepository.findByMail(testUserMail)).thenReturn(Optional.of(testUser));

        // When
        readUserService.readUserByMail(testUserMail);

        // Then
        verify(userRepository, times(1)).findByMail(testUserMail);
    }

    @Test
    void readUserByMail_存在しないメールアドレスでの検索() {
        // Given
        String nonExistentMail = "nonexistent@example.com";
        when(userRepository.findByMail(nonExistentMail)).thenReturn(Optional.empty());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> readUserService.readUserByMail(nonExistentMail)
        );

        assertEquals("User not found with email: " + nonExistentMail, exception.getMessage());
        verify(userRepository).findByMail(nonExistentMail);
    }
}
