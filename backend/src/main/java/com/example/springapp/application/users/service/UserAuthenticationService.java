package com.example.springapp.application.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springapp.domain.users.irepositoryinterface.IUserRepositoryInterface;

// CustomUserDetailsServiceは、Spring SecurityのUserDetailsServiceを実装し、ユーザー情報を取得し、認証を行います。
@Service
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private IUserRepositoryInterface userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.springapp.domain.users.domainobject.User user = userRepository.findByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return User.builder()
                .username(user.getMail())
                .password(user.getPassword()) // パスワードはエンコード済みである必要があります
                .roles("USER") // 権限を設定
                .build();
    }
}

