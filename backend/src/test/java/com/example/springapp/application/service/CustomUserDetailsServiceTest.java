package com.example.springapp.application.service;

import com.example.springapp.domain.domainobject.User;
import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private IUserRepositoryInterface userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(
            UUID.randomUUID(),
            "田中",
            "タナカ",
            "太郎",
            "タロウ",
            "test@example.com",
            "encodedPassword",
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    @Test
    void loadUserByUsername_正常ケース() {
        // Given
        when(userRepository.findByMail("test@example.com")).thenReturn(Optional.of(testUser));

        // When
        UserDetails result = customUserDetailsService.loadUserByUsername("test@example.com");

        // Then
        assertNotNull(result);
        assertEquals("test@example.com", result.getUsername());
        assertEquals("encodedPassword", result.getPassword());
        assertTrue(result.isEnabled());
        assertTrue(result.isAccountNonExpired());
        assertTrue(result.isAccountNonLocked());
        assertTrue(result.isCredentialsNonExpired());
        verify(userRepository).findByMail("test@example.com");
    }

    @Test
    void loadUserByUsername_ユーザーが存在しない場合_例外が発生() {
        // Given
        when(userRepository.findByMail(anyString())).thenReturn(Optional.empty());

        // When & Then
        UsernameNotFoundException exception = assertThrows(
            UsernameNotFoundException.class,
            () -> customUserDetailsService.loadUserByUsername("nonexistent@example.com")
        );

        assertEquals("User not found with email: nonexistent@example.com", exception.getMessage());
        verify(userRepository).findByMail("nonexistent@example.com");
    }

    @Test
    void loadUserByUsername_リポジトリが正しく呼び出される() {
        // Given
        when(userRepository.findByMail("test@example.com")).thenReturn(Optional.of(testUser));

        // When
        customUserDetailsService.loadUserByUsername("test@example.com");

        // Then
        verify(userRepository, times(1)).findByMail("test@example.com");
    }
}
