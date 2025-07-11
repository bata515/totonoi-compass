package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.UserViewModel;
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

    public User createUser(UserViewModel userViewModel) {
        // メールが既に存在するかを確認
        if (userRepository.findByMail(userViewModel.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }
        User user = new User(
                UUID.randomUUID(),
                userViewModel.getFamilyName(),
                userViewModel.getFamilyNameRuby(),
                userViewModel.getFirstName(),
                userViewModel.getFirstNameRuby(),
                userViewModel.getMail(),
                // パスワードを暗号化
                passwordEncoder.encode(userViewModel.getPassword()),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        return userRepository.createUser(user);
    }
}
