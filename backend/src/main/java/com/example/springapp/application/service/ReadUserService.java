package com.example.springapp.application.service;

import com.example.springapp.application.viewmodel.UserViewModel;
import com.example.springapp.domain.domainobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;

import java.util.UUID;

@Service
public class ReadUserService {
    @Autowired
    IUserRepositoryInterface userRepository;

    @Transactional
    public UserViewModel readUserById(UUID id) {
        User user =
                this.userRepository.findById(id);

        return UserViewModel.adaptToUserViewModel(user.getId(), user.getFamilyName(),user.getFamilyNameRuby(), user.getFirstName(),
                user.getFirstNameRuby(), user.getMail(),user.getPassword());
    }

    @Transactional
    public UserViewModel readUserByMail(String mail) {
        User user = this.userRepository.findByMail(mail)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + mail));

        return UserViewModel.adaptToUserViewModel(user.getId(), user.getFamilyName(),user.getFamilyNameRuby(), user.getFirstName(),
                user.getFirstNameRuby(), user.getMail(),user.getPassword());
    }

}
