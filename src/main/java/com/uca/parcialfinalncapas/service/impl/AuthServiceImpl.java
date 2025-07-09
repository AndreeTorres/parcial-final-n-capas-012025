package com.uca.parcialfinalncapas.service.impl;


import com.uca.parcialfinalncapas.dto.request.LoginRequest;
import com.uca.parcialfinalncapas.dto.response.AuthResponse;
import com.uca.parcialfinalncapas.entities.User;
import com.uca.parcialfinalncapas.repository.iUserRepository;
import com.uca.parcialfinalncapas.service.JwtService;
import com.uca.parcialfinalncapas.service.iAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements iAuthService {

    private final iUserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByCorreo(request.getEmail()).orElse(null);

        if (user == null || !request.getPassword().equals(user.getPassword())) {
            return AuthResponse.builder()
                    .token(null)
                    .message("Usuario y/o contraseña inválidos")
                    .build();
        }

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .message("Inicio de sesión exitoso")
                .build();
    }
}
