package com.uca.parcialfinalncapas.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.uca.parcialfinalncapas.repository.iUserRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final  iUserRepository userRepository;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Encargado de gestionar la autenticacion de usuarios en spring Security

    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService()); // Cargar los datos desde la base de datos
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
      }
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected UserDetailsService userDetailService() {
        return email -> userRepository.findByCorreo(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
