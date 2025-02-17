package com.codehive.gestao_de_pedidos.repository;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {
    Optional<UsuarioModel> findByEmail(String email);
}
