package com.example.springapp.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springapp.application.service.ReadUserService;

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
