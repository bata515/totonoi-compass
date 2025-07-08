package com.example.springapp.application.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.springapp.application.users.bodymodel.CreateUserBodyModel;
import com.example.springapp.application.users.service.CreateUserService;
import com.example.springapp.application.users.service.ReadUserService;
import com.example.springapp.application.users.viewmodel.UserViewModel;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    CreateUserService createUserService;

    @GetMapping("/signup")
    public String showSignUpPage(Model model) {
        model.addAttribute("createUserBodyModel", new CreateUserBodyModel());
        return "signup";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("createUserBodyModel") CreateUserBodyModel createUserBodyModel) {
        createUserService.createUser(createUserBodyModel);
        return "redirect:/login"; // 登録後はログインページへリダイレクト
    }
}
