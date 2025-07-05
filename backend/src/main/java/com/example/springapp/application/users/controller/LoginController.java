package com.example.springapp.application.users.controller;

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
    public String loginPage() {
        return "login";  
    }

    /**
     * ユーザー登録ページを表示する
     * @return register.htmlテンプレートを返す
     */
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }

    /**
     * ホームページ（index.html）を表示する
     * ログイン成功後にSpring Securityによってリダイレクトされる
     * @param model テンプレートにデータを渡すためのModelオブジェクト
     * @return index.htmlテンプレートを返す
     */
    @GetMapping("/")
    public String home(Model model) {
        // Spring Securityのセキュリティコンテキストから認証情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // 認証されたユーザーのメールアドレス（ユーザー名）を取得
        String userEmail = authentication.getName();
        
        // メールアドレスを使ってデータベースからユーザー情報を取得
        UserViewModel user = readUserService.readUserByMail(userEmail);
        
        // 取得したユーザー情報をテンプレートに渡す
        model.addAttribute("user", user);
        
        // index.htmlテンプレートを返す
        return "index";
    }
}
