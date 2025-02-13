package com.codehive.gestao_de_pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;

    @Min(value = 0, message = "O preço deve ser positivo")
    private double preco;

    @Min(value = 0, message = "O estoque deve ser positivo")
    private int estoque;
}
