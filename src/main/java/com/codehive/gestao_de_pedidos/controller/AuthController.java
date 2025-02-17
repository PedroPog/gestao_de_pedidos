package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody com.codehive.gestao_de_pedidos.model.dto.LoginDTO loginDTO) {
        String token = authService.autenticar(loginDTO);

        if (token != null) {
            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
