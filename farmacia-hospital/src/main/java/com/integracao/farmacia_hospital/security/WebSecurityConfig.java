package com.integracao.farmacia_hospital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private FarmaciaUserDetailsService farmaciaUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/medicos/registrar", "/farmacias/registrar").permitAll()
                                .requestMatchers("/receitas/enviar/**").hasRole("MEDICO")
                                .requestMatchers("/receitas/receber/**").hasRole("FARMACIA")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider medicoProvider = new DaoAuthenticationProvider();
        medicoProvider.setUserDetailsService(userDetailsService);
        medicoProvider.setPasswordEncoder(passwordEncoder);

        DaoAuthenticationProvider farmaciaProvider = new DaoAuthenticationProvider();
        farmaciaProvider.setUserDetailsService(farmaciaUserDetailsService);
        farmaciaProvider.setPasswordEncoder(passwordEncoder);

        auth.authenticationProvider(medicoProvider);
        auth.authenticationProvider(farmaciaProvider);
    }
}
