package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.ItemPedidoModel;
import com.codehive.gestao_de_pedidos.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public List<ItemPedidoModel> listarItensPedido() {
        return repository.findAll();
    }

    public Optional<ItemPedidoModel> buscarPorId(int id) {
        return repository.findById(id);
    }

    public ItemPedidoModel salvarItemPedido(ItemPedidoModel itemPedido) {
        return repository.save(itemPedido);
    }

    public void deletarItemPedido(int id) {
        repository.deleteById(id);
    }
}
