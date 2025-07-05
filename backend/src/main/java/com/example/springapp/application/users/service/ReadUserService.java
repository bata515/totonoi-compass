package com.example.springapp.application.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springapp.application.users.viewmodel.UserViewModel;
import com.example.springapp.domain.users.domainobject.User;
import com.example.springapp.domain.users.irepositoryinterface.IUserRepositoryInterface;

import java.util.UUID;

@Service
public class ReadUserService {
    @Autowired
    IUserRepositoryInterface userRepository;

    @Transactional
    public UserViewModel readUserById(UUID id) {
        User user =
                this.userRepository.findById(id);

        return UserViewModel.adaptToUserVewModel(user.getId(), user.getFamilyName(), user.getFirstName(),
                user.getMail());
    }

    @Transactional
    public UserViewModel readUserByMail(String mail) {
        User user = this.userRepository.findByMail(mail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + mail));

        return UserViewModel.adaptToUserVewModel(user.getId(), user.getFamilyName(), user.getFirstName(),
                user.getMail());
    }

}
