package com.example.hustled.config;

import com.example.hustled.service.CustomUserDetailsService;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(CustomUserDetailsService userDetailsService,
                                                  BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/register", "/login","/job-grid","/candidate-grid", "/candidate**","/employer**","/modal/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin-profile").hasAuthority("ADMIN")
                        .requestMatchers("/employer-profile","/employer-manage-jobs","/employer-transaction","/employer-change-password").hasAuthority("EMPLOYER")
                        .requestMatchers("/candidate-profile","/candidate-chat","/candidate-my-resume","/candidate-jobs-applied","/candidate-saved-jobs","/candidate-change-password").hasAuthority("CANDIDATE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/index?showLogin=true")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/route-by-role", true)
                        .permitAll()
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecretKey") // 🔐 change this to a secure random string
                        .rememberMeParameter("remember-me") // matches your form input name
                        .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/index")
                        .permitAll()
                );
        return http.build();
    }
}
