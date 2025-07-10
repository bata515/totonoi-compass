package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.CreateUserViewModel;
import com.example.springapp.domain.domainobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateUserService {

    @Autowired
    private IUserRepositoryInterface userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateUserViewModel createUserViewModel) {
        // メールが既に存在するかを確認
        if (userRepository.findByMail(createUserViewModel.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
        User user = new User(
                UUID.randomUUID(),
                createUserViewModel.getFamilyName(),
                createUserViewModel.getFamilyNameRuby(),
                createUserViewModel.getFirstName(),
                createUserViewModel.getFirstNameRuby(),
                createUserViewModel.getMail(),
                // パスワードを暗号化
                passwordEncoder.encode(createUserViewModel.getPassword()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return userRepository.createUser(user);
    }
}
