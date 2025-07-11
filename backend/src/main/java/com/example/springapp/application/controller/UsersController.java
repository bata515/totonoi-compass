package com.example.springapp.application.controller;

import com.example.springapp.application.viewmodel.UserViewModel;
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
    model.addAttribute("userViewModel", new UserViewModel(null,null,null,null,
            null,null,null));
        return "signup";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("userViewModel") UserViewModel userViewModel) {
        createUserService.createUser(userViewModel);
        return "redirect:/login"; // 登録後はログインページへリダイレクト
    }
}
