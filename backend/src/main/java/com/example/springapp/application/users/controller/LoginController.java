package com.example.springapp.application.users.controller;

import com.example.springapp.application.users.bodymodel.CreateUserBodyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springapp.application.users.service.ReadUserService;
import com.example.springapp.application.users.viewmodel.UserViewModel;

@Controller
public class LoginController {

    @Autowired
    private ReadUserService readUserService;

    /**
     * ログインページを表示する
     * @return login.htmlテンプレートを返す
     */
    @GetMapping("/login")
    public String readLoginPage() {
        return "login";  
    }
}
