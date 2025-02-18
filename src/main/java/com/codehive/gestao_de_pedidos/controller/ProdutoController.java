package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.ProdutoModel;
import com.codehive.gestao_de_pedidos.security.JwtUtil;
import com.codehive.gestao_de_pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    private JwtUtil jwtUtil = new JwtUtil();

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
    public ResponseEntity<?> criar(@RequestBody ProdutoModel produto,@RequestHeader String token) {
        var validacao = jwtUtil.validarTokenAdmin(token);
        if(!validacao.equals(ResponseEntity.ok("Usuário autenticado com sucesso!"))){
            return validacao;
        }
        return ResponseEntity.ok().body(service.salvarProduto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
