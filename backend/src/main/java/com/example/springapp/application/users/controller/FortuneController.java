package com.example.springapp.application.users.controller;

import com.example.springapp.application.users.bodymodel.CreateUserBodyModel;
import com.example.springapp.application.users.service.ReadSaunaService;
import com.example.springapp.application.users.viewmodel.SaunaViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FortuneController {
    @Autowired
    ReadSaunaService readSaunaService;

    @GetMapping("/fortune")
    public String readFortunePage() {

        return "fortune";
    }

    @PostMapping("/fortune/result")
    public String showFortune(Model model) {
        // Spring Securityのセキュリティコンテキストから認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 認証されたユーザーのメールアドレスを取得
        String userMail = authentication.getName();

        Optional<SaunaViewModel> saunaViewModelOptional = readSaunaService.readSaunaListByUserMail(userMail);

        if (saunaViewModelOptional.isEmpty()) {
            model.addAttribute("error", "サウナの情報が見つかりませんでした。");
            return "result"; // result.htmlにエラーメッセージを表示
        }

        model.addAttribute("sauna", saunaViewModelOptional.get());
        return "result"; // result.htmlを表示
    }
}
