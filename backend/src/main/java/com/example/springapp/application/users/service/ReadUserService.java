package com.example.springapp.application.users.service;

import com.example.springapp.domain.domainobject.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springapp.application.users.viewmodel.UserViewModel;
import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;

import java.util.UUID;

@Service
public class ReadUserService {
    @Autowired
    IUserRepositoryInterface userRepository;

    @Transactional
    public UserViewModel readUserById(UUID id) {
        Users users =
                this.userRepository.findById(id);

        return UserViewModel.adaptToUserViewModel(users.getId(), users.getFamilyName(), users.getFirstName(),
                users.getMail());
    }

    @Transactional
    public UserViewModel readUserByMail(String mail) {
        Users users = this.userRepository.findByMail(mail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + mail));

        return UserViewModel.adaptToUserViewModel(users.getId(), users.getFamilyName(), users.getFirstName(),
                users.getMail());
    }

}
