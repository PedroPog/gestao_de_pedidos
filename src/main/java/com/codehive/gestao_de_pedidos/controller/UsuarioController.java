package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import com.codehive.gestao_de_pedidos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioModel> listarTodos() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable int id) {
        Optional<UsuarioModel> usuario = service.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioModel criar(@RequestBody UsuarioModel usuario) {
        return service.salvarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
