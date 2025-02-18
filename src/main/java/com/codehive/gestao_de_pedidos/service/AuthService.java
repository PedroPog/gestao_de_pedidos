package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import com.codehive.gestao_de_pedidos.model.dto.LoginDTO;
import com.codehive.gestao_de_pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Map<String, Object> autenticarUsuario(LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(loginDTO.getEmail());

        if (usuario.isPresent() && usuario.get().getSenha().equals(loginDTO.getSenha())) {
            response.put("id", usuario.get().getId());  // Retorna o ID do usuário
            response.put("message", "Login realizado com sucesso!");
        } else {
            response.put("message", "Credenciais inválidas!");
        }

        return response;
    }
}
