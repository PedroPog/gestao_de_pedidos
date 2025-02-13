package com.codehive.gestao_de_pedidos.repository;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {
}
