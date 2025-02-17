package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import com.codehive.gestao_de_pedidos.model.dto.LoginDTO;
import com.codehive.gestao_de_pedidos.repository.UsuarioRepository;
import com.codehive.gestao_de_pedidos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String autenticar(LoginDTO loginDTO) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByEmail(loginDTO.getEmail());

        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();

            if (passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
                return jwtUtil.generateToken(usuario.getEmail());
            }
        }
        return null;  // Login falhou
    }

}
