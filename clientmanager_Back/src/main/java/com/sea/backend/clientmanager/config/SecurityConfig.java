package com.sea.backend.clientmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Configura a cadeia de filtros de segurança da aplicação (SecurityFilterChain).
     *
     * @param http objeto de configuração fornecido pelo Spring Security
     * @return instância configurada de SecurityFilterChain
     * @throws Exception caso ocorra erro durante a configuração
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeHttpRequests(auth -> auth
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/clientes/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic();

        return http.build();
    }

    /**
     * Define um serviço de autenticação baseado em usuários em memória.
     * As senhas são codificadas com BCrypt.
     *
     * @return instância de UserDetailsService para autenticação em memória
     */
    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123qwe!@#"))
                .roles("ADMIN")
                .build(),
            User.builder()
                .username("user")
                .password(passwordEncoder().encode("123qwe123"))
                .roles("USER")
                .build()
        );
    }

    /**
     * Define um bean responsável por codificar senhas utilizando o algoritmo BCrypt.
     *
     * O BCrypt aplica hashing com salt, garantindo segurança adequada para senhas
     * armazenadas e comparações durante o processo de autenticação.
     *
     * @return instância de BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
