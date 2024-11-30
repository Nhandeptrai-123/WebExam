package com.webquizz.webquizz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class springSecurity {
    private static final String[] CSS_JS = {
            "/", "/signup", "/finduser", "/login", "/createuser", "/createtest", "/library", "/kiemtra", "/questions", "/contact","/about","/example_2","/example_1",
            "/bootstrap.css","/create_name_exam.css","/create-name_exam.js","/createExample_1","/exam_1",
            "/responsive.css",
            "/style.css",
            "/bootstrap.js",
            "/dangky.css",
            "/dangnhap.css",
            "/jquery-3.4.1.min.js",
            "/about-img1.jpg", "/about-img2.jpg", "/blog1.jpg", "/blog2.jpg", "/envelope-white.png", "/fb.png", "/graduation-hat.png", "/insta.png",
            "/instagram.png", "/linkedin.png", "/location-white.png", "/logo.png", "/menu.png", "/next.png", "/plug.png","/e-learning-colored-composition-vector.jpg","/searching-e-learning-composition-vector.jpg",
            "/prev.png", "/s1.png", "/s2.png", "/s3.png", "/s4.png", "/s5.png", "/slider-img.jpg", "/telephone-white.png", "/twitter.png", "/youtube.png"
    };
    private final UserDetailsService userDetailsService;

    public springSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(CSS_JS).permitAll() // Đảm bảo tài nguyên tĩnh không yêu cầu đăng nhập
                        .anyRequest().permitAll() // Các yêu cầu khác cần đăng nhập
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true)
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/logout?expired")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                )
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID").invalidateHttpSession(true)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
}

