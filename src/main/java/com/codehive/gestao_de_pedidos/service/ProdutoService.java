package com.codehive.gestao_de_pedidos.service;

import com.codehive.gestao_de_pedidos.model.ProdutoModel;
import com.codehive.gestao_de_pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarProdutos() {
        return repository.findAll();
    }

    public Optional<ProdutoModel> buscarPorId(int id) {
        return repository.findById(id);
    }

    public ProdutoModel salvarProduto(ProdutoModel produto) {
        return repository.save(produto);
    }

    public void deletarProduto(int id) {
        repository.deleteById(id);
    }
}
