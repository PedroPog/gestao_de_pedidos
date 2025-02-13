package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.ItemPedidoModel;
import com.codehive.gestao_de_pedidos.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService service;

    @GetMapping
    public List<ItemPedidoModel> listarTodos() {
        return service.listarItensPedido();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoModel> buscarPorId(@PathVariable int id) {
        Optional<ItemPedidoModel> itemPedido = service.buscarPorId(id);
        return itemPedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemPedidoModel criar(@RequestBody ItemPedidoModel itemPedido) {
        return service.salvarItemPedido(itemPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletarItemPedido(id);
        return ResponseEntity.noContent().build();
    }
}
