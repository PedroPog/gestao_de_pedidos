package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.PedidoModel;
import com.codehive.gestao_de_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoModel> listarPedidos() {
        return repository.findAll();
    }

    public Optional<PedidoModel> buscarPorId(int id) {
        return repository.findById(id);
    }

    public PedidoModel salvarPedido(PedidoModel pedido) {
        return repository.save(pedido);
    }

    public void deletarPedido(int id) {
        repository.deleteById(id);
    }
}
