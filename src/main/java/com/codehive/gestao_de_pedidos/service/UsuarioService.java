package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import com.codehive.gestao_de_pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioModel> listarUsuarios() {
        return repository.findAll();
    }

    public Optional<UsuarioModel> buscarPorId(int id) {
        return repository.findById(id);
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario) {
        return repository.save(usuario);
    }

    public void deletarUsuario(int id) {
        repository.deleteById(id);
    }
}
