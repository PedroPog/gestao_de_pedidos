package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.RoleName;
import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import com.codehive.gestao_de_pedidos.model.dto.RetornoUsuario;
import com.codehive.gestao_de_pedidos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<RetornoUsuario> listarUsuarios() {
        return repository.findAll().stream().map(usuarioModel -> {
            RetornoUsuario retorno = new RetornoUsuario();
            retorno.setId(usuarioModel.getId());
            retorno.setNome(usuarioModel.getNome());
            retorno.setEmail(usuarioModel.getEmail());
            retorno.setRoleStatus(usuarioModel.getRoleStatus());
            return retorno;
        }).toList();
    }


    public Optional<UsuarioModel> buscarPorId(int id) {
        return repository.findById(id);
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario) {
        return repository.save(usuario);
    }

    public void deletarUsuario(int id) {
        repository.updateRoleStatusById(id, RoleName.ROLE_INATIVO);
    }
}
