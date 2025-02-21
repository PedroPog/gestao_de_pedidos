package com.codehive.gestao_de_pedidos.controller;

import com.codehive.gestao_de_pedidos.model.ProdutoModel;
import com.codehive.gestao_de_pedidos.model.RoleName;
import com.codehive.gestao_de_pedidos.security.JwtUtil;
import com.codehive.gestao_de_pedidos.service.AuthService;
import com.codehive.gestao_de_pedidos.service.ProdutoService;
import com.codehive.gestao_de_pedidos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    @Autowired
    private AuthService authService;
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
        String email = "";
        Map<String, String> validacao = jwtUtil.validarTokenAdmin(token);
        if("valido".equals(validacao.get("status"))){
            RoleName roleName = authService.verificarRoleUser(validacao.get("email"));
            if(roleName.equals(RoleName.ROLE_ADMINS)){
                return ResponseEntity.ok().body(service.salvarProduto(produto));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autorizado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
