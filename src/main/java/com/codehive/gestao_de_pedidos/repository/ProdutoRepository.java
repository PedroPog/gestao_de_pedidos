package com.codehive.gestao_de_pedidos.repository;

import com.codehive.gestao_de_pedidos.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
}
