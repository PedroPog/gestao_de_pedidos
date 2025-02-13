package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.PedidoModel;
import com.codehive.gestao_de_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<PedidoModel> listarTodos() {
        return service.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscarPorId(@PathVariable int id) {
        Optional<PedidoModel> pedido = service.buscarPorId(id);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PedidoModel criar(@RequestBody PedidoModel pedido) {
        return service.salvarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
