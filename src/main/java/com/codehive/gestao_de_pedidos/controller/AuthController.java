package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.dto.LoginDTO;
import com.codehive.gestao_de_pedidos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = authService.autenticarUsuario(loginDTO);

        if (response.containsKey("id")) {
            return ResponseEntity.ok()
                    .header("User-ID", response.get("id").toString()) // Adiciona o ID no header
                    .body(response.get("message").toString()); // Retorna a mensagem no body
        } else {
            return ResponseEntity.status(401).body(response.get("message").toString());
        }
    }
}
