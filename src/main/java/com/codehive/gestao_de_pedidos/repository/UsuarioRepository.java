package com.codehive.gestao_de_pedidos.repository;

import com.codehive.gestao_de_pedidos.model.UsuarioModel;
import com.codehive.gestao_de_pedidos.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Integer> {
    Optional<UsuarioModel> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE UsuarioModel u SET u.roleStatus = :role WHERE u.id = :id")
    void updateRoleStatusById(int id, RoleName role);
}
