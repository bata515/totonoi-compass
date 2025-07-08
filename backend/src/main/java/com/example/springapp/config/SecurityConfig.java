package com.example.springapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Springの設定クラスであることを示すアノテーション
@EnableWebSecurity // これにより、Spring Bootの自動構成（auto configuration）はオフになり、このクラスの設定が使われるようになります。

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/users/create","/users/signup").permitAll()  // 指定のリクエストは認証なしでアクセス許可します
                        .anyRequest().authenticated()  // 他のページは認証が必要
                )
                .formLogin(form -> form
                        .loginPage("/login")  // カスタムログインページを指定
                        .usernameParameter("mail")  // ユーザー名パラメータをmailに設定
                        .passwordParameter("password")  // パスワードパラメータを明示的に設定
                        // ↑userDetailsServiceを呼び出し、ユーザー情報の照合を自動的に行います。
                        .defaultSuccessUrl("/", true)  // ログイン成功時はindex.htmlに遷移
                        .failureUrl("/login?error=true")  // ログイン失敗時はエラーパラメータ付きでログインページに戻る
                        .permitAll()  // ログインページは認証なしで許可します
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // ログアウトURL
                        .permitAll()
                );
        return http.build();
    }

    @Bean // このメソッドの返り値をSpringコンテナにBeanとして登録する
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoderをインスタンス化して返す
    }

}
