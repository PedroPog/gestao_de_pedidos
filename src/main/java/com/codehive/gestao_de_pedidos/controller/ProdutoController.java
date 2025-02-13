package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.ProdutoModel;
import com.codehive.gestao_de_pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<ProdutoModel> listarTodos() {
        return service.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarPorId(@PathVariable int id) {
        Optional<ProdutoModel> produto = service.buscarPorId(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProdutoModel criar(@RequestBody ProdutoModel produto) {
        return service.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
