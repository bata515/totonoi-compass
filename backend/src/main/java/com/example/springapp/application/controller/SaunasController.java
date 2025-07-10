package com.example.springapp.application.controller;

import com.example.springapp.application.service.CreateSaunaService;
import com.example.springapp.application.service.ReadSaunaService;
import com.example.springapp.application.service.UpdateSaunaService;
import com.example.springapp.application.viewmodel.SaunaViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/saunas")
public class SaunasController {
    @Autowired
    ReadSaunaService readSaunaService;
    @Autowired
    CreateSaunaService createSaunaService;
    @Autowired
    UpdateSaunaService updateSaunaService;

    @GetMapping
    public String readSaunasPage(Model model) {
        // Spring Securityのセキュリティコンテキストから認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 認証されたユーザーのメールアドレスを取得
        String userMail = authentication.getName();
        List<SaunaViewModel> saunaViewModelList = this.readSaunaService.readSaunaListsByUserMail(userMail);

        model.addAttribute("saunas", saunaViewModelList);
        return "list";
    }

    @GetMapping("/new")
    public String readCreateSaunaPage(Model model) {
        model.addAttribute("sauna", new SaunaViewModel(null,null,null,null,false));
        return "form";
    }

    @PostMapping("/create")
    public String createSauna(@ModelAttribute("sauna") SaunaViewModel saunaViewModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();

        SaunaViewModel createSaunaViewModel =
                SaunaViewModel.adaptToSaunaViewModel(saunaViewModel.getId(),userMail,saunaViewModel.getName(),
                        saunaViewModel.getUrl(),saunaViewModel.getVisited());
        this.createSaunaService.createSauna(createSaunaViewModel);
        return "redirect:/saunas";
    }

    @GetMapping("/{id}/edit")
    public String readEditSaunaPage(@PathVariable UUID id, Model model) {
        SaunaViewModel sauna = this.readSaunaService.readSaunaById(id); // 例：Optional外したサービス
        model.addAttribute("sauna", sauna);
        return "form";
    }

    @PostMapping("/{id}/update")
    public String updateSauna(@PathVariable UUID id, @ModelAttribute("sauna") SaunaViewModel saunaViewModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();

        SaunaViewModel updateSaunaViewModel =
                SaunaViewModel.adaptToSaunaViewModel(id,userMail,saunaViewModel.getName(),
                        saunaViewModel.getUrl(),saunaViewModel.getVisited()
                        );

        this.updateSaunaService.updateSauna(updateSaunaViewModel);
        return "redirect:/saunas";
    }


}
