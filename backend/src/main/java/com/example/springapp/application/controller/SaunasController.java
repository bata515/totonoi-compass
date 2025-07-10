package com.example.springapp.application.controller;

import com.example.springapp.application.service.ReadSaunaService;
import com.example.springapp.application.viewmodel.SaunaViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/saunas")
public class SaunasController {
    @Autowired
    ReadSaunaService readSaunaService;

    @GetMapping
    public String readSaunasPage(Model model) {
        // Spring Securityのセキュリティコンテキストから認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 認証されたユーザーのメールアドレスを取得
        String userMail = authentication.getName();
        List<SaunaViewModel> saunaViewModelList = readSaunaService.readSaunaListsByUserMail(userMail);

        model.addAttribute("saunas", saunaViewModelList);
        return "list";
    }


}
