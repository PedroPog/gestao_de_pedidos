package com.codehive.gestao_de_pedidos.repository;

import com.codehive.gestao_de_pedidos.model.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Integer> {
}
