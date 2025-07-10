package com.example.springapp.application.service;

import com.example.springapp.domain.domainobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springapp.domain.irepositoryinterface.IUserRepositoryInterface;

// CustomUserDetailsServiceは、Spring SecurityのUserDetailsServiceを実装し、ユーザー情報を取得し、認証を行います。
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepositoryInterface userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getMail())
                .password(user.getPassword()) // 暗号化されたパスワードを使用
                .build();
    }
}

