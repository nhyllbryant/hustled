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

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) //Lambda
//                //.csrf(AbstractHttpConfigurer::disable) //MethodReference
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/","/index", "/modal/**", "/login", "/login/**", "/register","/modal/register", "/process-register").permitAll()
//                        .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/webjars/**").permitAll() // ✅ static resources
//                        .requestMatchers("/jobs/**").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/process-login")
//                        .defaultSuccessUrl("/jobs", true)
//                        .failureUrl("/login?error=true")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/index")
//                        .permitAll()
//                )
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/register", "/login", "/modal/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin-profile").hasAuthority("ADMIN")
                        .requestMatchers("/employer-profile").hasAuthority("EMPLOYER")
                        .requestMatchers("/candidate-profile").hasAuthority("CANDIDATE")
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
