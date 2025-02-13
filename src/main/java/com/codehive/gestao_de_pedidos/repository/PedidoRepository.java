package com.codehive.gestao_de_pedidos.repository;

import com.codehive.gestao_de_pedidos.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
}
