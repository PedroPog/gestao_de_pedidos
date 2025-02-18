package com.codehive.gestao_de_pedidos.model.dto;

import com.codehive.gestao_de_pedidos.model.RoleName;

public class RetornoUsuario {

    private int id;
    private String nome;
    private String email;
    private RoleName roleStatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleName getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(RoleName roleStatus) {
        this.roleStatus = roleStatus;
    }
}
