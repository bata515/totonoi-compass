package com.example.springapp.application.controller;

import com.example.springapp.application.service.ReadSaunaService;
import com.example.springapp.application.service.ReadUserService;
import com.example.springapp.application.viewmodel.SaunaViewModel;
import com.example.springapp.application.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SuggestController {
    @Autowired
    ReadSaunaService readSaunaService;
    @Autowired
    ReadUserService readUserService;

    @GetMapping("/suggest")
    public String readSuggestPage(Model model) {
        // Spring Securityのセキュリティコンテキストから認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 認証されたユーザーのメールアドレスを取得
        String userMail = authentication.getName();

        UserViewModel userViewModel =
        this.readUserService.readUserByMail(userMail);

        model.addAttribute("username", userViewModel.getFamilyName()+userViewModel.getFirstName());
        return "suggest";
    }

    @PostMapping("/suggest/result")
    public String suggest(Model model) {
        // Spring Securityのセキュリティコンテキストから認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 認証されたユーザーのメールアドレスを取得
        String userMail = authentication.getName();

        Optional<SaunaViewModel> saunaViewModelOptional = this.readSaunaService.readRandomSaunaByUserMail(userMail);

        if (saunaViewModelOptional.isEmpty()) {
            model.addAttribute("error", "サウナの情報が見つかりませんでした。");
            return "result"; // result.htmlにエラーメッセージを表示
        }

        model.addAttribute("sauna", saunaViewModelOptional.get());
        return "result"; // result.htmlを表示
    }
}
