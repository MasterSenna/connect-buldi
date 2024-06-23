package com.connectMentor.auth.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/login", HttpMethod.POST.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/register", HttpMethod.POST.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/index", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mentorado/perfilMentorado", HttpMethod.GET.name())).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/mentor/perfilMentor", HttpMethod.GET.name())).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/againLogin", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/sobreNos", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/logar", HttpMethod.POST.name())).permitAll()
                        .requestMatchers(HttpMethod.POST, "/mentor/inserirMentores").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mentor/index").permitAll()
                        .requestMatchers(HttpMethod.POST, "/mentorado/inserirMentorados").permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mentor/sucesso", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mentorado/avaliacao", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mentor/avaliacao", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mentor/CadastroMentor", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/mentorado/CadastroMentorado", HttpMethod.GET.name())).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/static/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/assets/**")).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
