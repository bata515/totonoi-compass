package com.example.springapp.application.controller;

import com.example.springapp.application.viewmodel.CreateUserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.springapp.application.service.CreateUserService;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    CreateUserService createUserService;

    @GetMapping("/signup")
    public String readSignUpPage(Model model) {
        model.addAttribute("createUserViewModel", new CreateUserViewModel());
        return "signup";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("createUserViewModel") CreateUserViewModel createUserViewModel) {
        createUserService.createUser(createUserViewModel);
        return "redirect:/login"; // 登録後はログインページへリダイレクト
    }
}
