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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private IUserRepositoryInterface userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserService createUserService;

    private UserViewModel userViewModel;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        userViewModel = new UserViewModel(
            null,
            "田中",
            "タナカ",
            "太郎",
            "タロウ",
            "test@example.com",
            "password123"
        );

        expectedUser = new User(
            UUID.randomUUID(),
            "田中",
            "タナカ",
            "太郎",
            "タロウ",
            "test@example.com",
            "encodedPassword",
            null,
            null
        );
    }

    @Test
    void createUser_正常ケース() {
        // Given
        when(userRepository.findByMail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.createUser(any(User.class))).thenReturn(expectedUser);

        // When
        User result = createUserService.createUser(userViewModel);

        // Then
        assertNotNull(result);
        assertEquals(expectedUser.getMail(), result.getMail());
        verify(userRepository).findByMail("test@example.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).createUser(any(User.class));
    }

    @Test
    void createUser_メールが既に存在する場合_例外が発生() {
        // Given
        when(userRepository.findByMail(anyString())).thenReturn(Optional.of(expectedUser));

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> createUserService.createUser(userViewModel)
        );

        assertEquals("Email is already registered", exception.getMessage());
        verify(userRepository).findByMail("test@example.com");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).createUser(any(User.class));
    }

    @Test
    void createUser_パスワードが暗号化される() {
        // Given
        when(userRepository.findByMail(anyString())).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.createUser(any(User.class))).thenReturn(expectedUser);

        // When
        createUserService.createUser(userViewModel);

        // Then
        verify(passwordEncoder).encode("password123");
        verify(userRepository).createUser(argThat(user -> 
            user.getPassword().equals("encodedPassword")
        ));
    }
}
